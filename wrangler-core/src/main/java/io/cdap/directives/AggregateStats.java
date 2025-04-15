package io.cdap.wrangler.directive;

import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.DirectiveContext;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.annotation.Description;
import io.cdap.wrangler.api.annotation.Name;
import io.cdap.wrangler.api.annotation.SchemaAttribute;
import io.cdap.wrangler.api.annotation.Tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Name("aggregate-stats")
@Description("Aggregates statistics such as count, sum, and average for a given column.")
@Tags(tags = { "aggregate", "stats", "summary" })
@SchemaAttribute(name = "aggregate-stats", type = "operation")
public class AggregateStats implements Directive {
    private String column;

    @Override
    public void initialize(DirectiveContext ctx) throws Exception {
        column = ctx.getArguments().value("column");
    }

    @Override
    public List<Row> execute(List<Row> rows) {
        double sum = 0.0;
        int count = 0;

        for (Row row : rows) {
            Object value = row.getValue(column);
            if (value instanceof Number) {
                sum += ((Number) value).doubleValue();
                count++;
            }
        }

        double avg = count > 0 ? sum / count : 0;
        List<Row> output = new ArrayList<>();
        Map<String, Object> stats = new HashMap<>();
        stats.put(column + "_count", count);
        stats.put(column + "_sum", sum);
        stats.put(column + "_avg", avg);
        output.add(new Row(stats));

        return output;
    }
}
