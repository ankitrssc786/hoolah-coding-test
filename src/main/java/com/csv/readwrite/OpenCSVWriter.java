package com.csv.readwrite;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OpenCSVWriter {

	private static final String STRING_ARRAY_SAMPLE = "./output.csv";
	private static final String SAMPLE_CSV_FILE_PATH = "./input.csv";
	private static final String INPUT_ARGUMENTS_SAMPLE = "./Input-arguments.csv";

	public static void main(String[] args)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		readAndWriteFromListOfObjects();
	}

	public static boolean between(String date, String dateStart, String dateEnd) throws ParseException {
		String sDate = date;
		String sFromDate = dateStart;
		String sToDate = dateEnd;
		DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DateFormat formatter3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date1 = formatter1.parse(sDate);
		Date date2 = formatter2.parse(sFromDate);
		Date date3 = formatter3.parse(sToDate);
		if (date1 != null && date3 != null && date3 != null) {
			if (date1.after(date2) && date1.before(date3)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	private static void readAndWriteFromListOfObjects()
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {

		int count = 0;
		try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));) {
			ColumnPositionMappingStrategy<MyUser> strategy = new ColumnPositionMappingStrategy<MyUser>();
			strategy.setType(MyUser.class);
			String[] memberFieldsToBindTo = { "id", "date", "amount", "merchant", "type", "transaction" };
			strategy.setColumnMapping(memberFieldsToBindTo);

			CsvToBean<MyUser> csvToBean = new CsvToBeanBuilder<MyUser>(reader).withMappingStrategy(strategy)
					.withSkipLines(1).withIgnoreLeadingWhiteSpace(true).build();

			Iterator<MyUser> myUserIterator = csvToBean.iterator();
			List<CSVResult> csvUsers = new ArrayList<>();

			while (myUserIterator.hasNext()) {
				MyUser myUser = myUserIterator.next();
				try (Reader reader1 = Files.newBufferedReader(Paths.get(INPUT_ARGUMENTS_SAMPLE));
						CSVReader csvReader = new CSVReader(reader1);) {
					// Reading Records One by One in a String array
					String[] nextRecord;

					while ((nextRecord = csvReader.readNext()) != null) {
						if (nextRecord[3].equalsIgnoreCase(myUser.getMerchant())) {
							if (between(myUser.getDate(), nextRecord[1], nextRecord[2])) {
								count++;
								try (Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));) {
									StatefulBeanToCsv<CSVResult> beanToCsv = new StatefulBeanToCsvBuilder<CSVResult>(
											writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
									csvUsers.add(new CSVResult(count, myUser.getAmount()));
									beanToCsv.write(csvUsers);
								}
							}
						}

					}
				}
			}
		}

	}
}