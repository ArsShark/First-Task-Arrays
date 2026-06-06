package by.grechanikovars.arraytask;

import by.grechanikovars.arraytask.comparator.ArrayFirstElementComparator;
import by.grechanikovars.arraytask.comparator.ArrayIdComparator;
import by.grechanikovars.arraytask.comparator.ArraySizeComparator;
import by.grechanikovars.arraytask.entity.IntArray;
import by.grechanikovars.arraytask.exception.ArrayException;
import by.grechanikovars.arraytask.factory.ArrayCreator;
import by.grechanikovars.arraytask.factory.impl.IntArrayCreatorImpl;
import by.grechanikovars.arraytask.parser.LineParser;
import by.grechanikovars.arraytask.parser.impl.IntLineParserImpl;
import by.grechanikovars.arraytask.reader.DataReader;
import by.grechanikovars.arraytask.reader.impl.FileDataReaderImpl;
import by.grechanikovars.arraytask.repository.ArrayRepository;
import by.grechanikovars.arraytask.repository.impl.ArrayRepositoryImpl;
import by.grechanikovars.arraytask.service.ArraySortService;
import by.grechanikovars.arraytask.service.ArrayStatService;
import by.grechanikovars.arraytask.service.impl.ArraySortServiceImpl;
import by.grechanikovars.arraytask.service.impl.ArrayStatServiceImpl;
import by.grechanikovars.arraytask.specification.ArraySpecification;
import by.grechanikovars.arraytask.specification.impl.FindByIdSpecificationImpl;
import by.grechanikovars.arraytask.specification.impl.FindByMaxGreaterThanSpecificationImpl;
import by.grechanikovars.arraytask.specification.impl.FindBySumGreaterThanSpecificationImpl;
import by.grechanikovars.arraytask.validator.DataValidator;
import by.grechanikovars.arraytask.validator.impl.ArrayDataValidatorImpl;
import by.grechanikovars.arraytask.warehouse.ArrayStatisticsData;
import by.grechanikovars.arraytask.warehouse.ArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Main {

  private static final Logger logger = LogManager.getLogger(Main.class);
  private static final String DATA_FILE = "data/arrays.txt";

  public static void main(String[] args) {
    DataReader reader = new FileDataReaderImpl();
    DataValidator validator = new ArrayDataValidatorImpl();
    LineParser parser = new IntLineParserImpl();
    ArrayCreator creator = new IntArrayCreatorImpl();
    ArrayStatService statService = new ArrayStatServiceImpl();
    ArraySortService sortService = new ArraySortServiceImpl();
    ArrayRepository repository = ArrayRepositoryImpl.getInstance();

    try {
      List<String> lines = reader.readLinesFromFile(DATA_FILE);
      for (String line : lines) {
        if (line.isBlank()) {
          logger.warn("Skipping blank line");
          continue;
        }
        if (!validator.isLineValid(line)) {
          logger.warn("Invalid line, extracting available integers: [{}]", line);
        }
        int[] numbers = parser.parseLine(line);
        if (numbers.length == 0) {
          logger.warn("No integers found in line: [{}]", line);
          continue;
        }
        IntArray array = creator.create(numbers);
        repository.add(array);
        logger.info("Added to repository: {}", array);
      }

      demonstratePart1(statService, sortService, repository);
      demonstratePart2(repository);

    } catch (ArrayException e) {
      logger.error("Fatal error: {}", e.getMessage(), e);
    }
  }

  private static void demonstratePart1(
          ArrayStatService statService,
          ArraySortService sortService,
          ArrayRepository repository) throws ArrayException {

    List<IntArray> all = repository.getAll();
    for (IntArray array : all) {
      Optional<Integer> min = statService.findMin(array);
      Optional<Integer> max = statService.findMax(array);
      Optional<Long> sum = statService.findSum(array);
      Optional<Double> avg = statService.findAverage(array);
      logger.info("Stats for {}: min={} max={} sum={} avg={}",
              array, min.orElse(null), max.orElse(null),
              sum.orElse(null), avg.orElse(null));
      int[] original = array.getElements();

      sortService.bubbleSort(array);
      logger.info("Bubble sort:    {}", array);
      array.setElements(original);

      sortService.selectionSort(array);
      logger.info("Selection sort: {}", array);
      array.setElements(original);
    }
  }

  private static void demonstratePart2(ArrayRepository repository) throws ArrayException {
    List<IntArray> all = repository.getAll();
    if (all.isEmpty()) {
      logger.warn("Repository is empty, skipping Part II demo");
      return;
    }

    ArrayWarehouse warehouse = ArrayWarehouse.getInstance();
    IntArray first = all.get(0);
    long firstId = first.getId();
    Optional<ArrayStatisticsData> statsOpt = warehouse.getStatistics(firstId);
    if (statsOpt.isPresent()) {
      ArrayStatisticsData stats = statsOpt.get();
      logger.info("Warehouse stats for id={}: {}", firstId, stats);
    }

    ArraySpecification byId = new FindByIdSpecificationImpl(firstId);
    List<IntArray> byIdResult = repository.findAll(byId);
    logger.info("findAll by id={}: {}", firstId, byIdResult);

    ArraySpecification bigSum = new FindBySumGreaterThanSpecificationImpl(10L);
    List<IntArray> bigSumResult = repository.findAllFunctional(bigSum);
    logger.info("findAllFunctional sum>10: {}", bigSumResult);

    ArraySpecification bigMax = new FindByMaxGreaterThanSpecificationImpl(50);
    List<IntArray> bigMaxResult = repository.findAll(bigMax);
    logger.info("findAll max>50: {}", bigMaxResult);

    repository.sort(new ArrayIdComparator());
    logger.info("After sort by id: {}", repository.getAll());

    repository.sort(new ArraySizeComparator());
    logger.info("After sort by size: {}", repository.getAll());

    repository.sort(new ArrayFirstElementComparator());
    logger.info("After sort by first element: {}", repository.getAll());

    IntArray target = all.get(0);
    logger.info("Before setElements: warehouse={}", warehouse.getStatistics(target.getId()));
    target.setElements(new int[]{1000, 2000, 3000});
    logger.info("After  setElements: warehouse={}", warehouse.getStatistics(target.getId()));

    boolean removed = repository.remove(firstId);
    logger.info("Removed id={}: {}", firstId, removed);
  }
}
