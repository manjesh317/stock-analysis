package aggregators;

import fileprocessors.StockFileReader;

import java.io.IOException;
import java.util.List;

public class AggregatorProcessor<T extends Aggregator> {
    T aggregator;
    String file;

    public AggregatorProcessor(T aggregator, String file) {
        this.aggregator = aggregator;
        this.file = file;
    }

    public double runAggregator(int colIndex) throws IOException {
        StockFileReader stockFileReader=new StockFileReader(file);
        List<String> lines=stockFileReader.readFileData();
        colIndex--;
        for(String line:lines){
            String[] numbers=line.split(",");
            double value=Double.parseDouble(numbers[colIndex]);
            aggregator.add(value);
        }
        double number=aggregator.calculate();
        return number;
    }
}
