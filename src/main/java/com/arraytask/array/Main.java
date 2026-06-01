package com.arraytask.array;

import com.arraytask.array.entity.IntArray;
import com.arraytask.array.exception.ArrayException;
import com.arraytask.array.factory.ArrayCreator;
import com.arraytask.array.factory.IntArrayCreator;
import com.arraytask.array.parser.IntLineParser;
import com.arraytask.array.parser.LineParser;
import com.arraytask.array.reader.DataReader;
import com.arraytask.array.reader.FileDataReader;
import com.arraytask.array.service.ArraySortService;
import com.arraytask.array.service.ArrayStatService;
import com.arraytask.array.service.impl.ArraySortServiceImpl;
import com.arraytask.array.service.impl.ArrayStatServiceImpl;
import com.arraytask.array.validator.ArrayDataValidator;
import com.arraytask.array.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DATA_FILE = "data/arrays.txt";

    public static void main(String[] args) {
        DataReader reader = new FileDataReader();
        DataValidator validator = new ArrayDataValidator();
        LineParser parser = new IntLineParser();
        ArrayCreator creator = new IntArrayCreator();
        ArrayStatService statService = new ArrayStatServiceImpl();
        ArraySortService sortService = new ArraySortServiceImpl();

        try {
            List<String> lines = reader.readLinesFromFile(DATA_FILE);
            for (String line : lines) {
                processLine(line, validator, parser, creator, statService, sortService);
            }
        } catch (ArrayException e) {
            logger.error("Fatal error while reading data file: {}", e.getMessage(), e);
        }
    }

    private static void processLine(
            String line,
            DataValidator validator,
            LineParser parser,
            ArrayCreator creator,
            ArrayStatService statService,
            ArraySortService sortService) throws ArrayException {

        if (line.isBlank()) {
            logger.warn("Skipping blank line");
            return;
        }
        if (!validator.isLineValid(line)) {
            logger.warn("Invalid line - extracting available integers: [{}]", line);
        }
        int[] numbers = parser.parseLine(line);
        if (numbers.length == 0) {
            logger.warn("No integers could be extracted from line: [{}]", line);
            return;
        }
        IntArray array = creator.create(numbers);
        logger.info("--- Array: {} ---", array);

        Optional<Integer> min = statService.findMin(array);
        Optional<Integer> max = statService.findMax(array);
        Optional<Long> sum = statService.findSum(array);
        Optional<Double> avg = statService.findAverage(array);

        logger.info("Min={} Max={} Sum={} Avg={}",
                min.orElse(null), max.orElse(null), sum.orElse(null), avg.orElse(null));

        IntArray bubbleSorted = creator.create(numbers);
        sortService.bubbleSort(bubbleSorted);
        logger.info("Bubble sort:    {}", bubbleSorted);

        IntArray selectionSorted = creator.create(numbers);
        sortService.selectionSort(selectionSorted);
        logger.info("Selection sort: {}", selectionSorted);
    }
}
