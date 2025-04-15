package io.cdap.wrangler.directive;

import io.cdap.wrangler.api.DirectiveContext;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.TestDirectiveContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AggregateStatsTest {

    @Test
    public void testAggregateStats() throws Exception {
        // Create input rows with values for aggregation
        List<Row> rows = new ArrayList<>();
        rows.add(new Row("value", 10));
        rows.add(new Row("value", 20));
        rows.add(new Row("value", 30));

        // Setup directive context and set the column argument
        DirectiveContext context = new TestDirectiveContext();
        context.getArguments().set("column", "value");

        // Initialize and execute the directive
        AggregateStats directive = new AggregateStats();
        directive.initialize(context);
        List<Row> result = directive.execute(rows);

        // Verify results
        Assert.assertEquals(1, result.size());

        Row statsRow = result.get(0);
        Assert.assertEquals(3, statsRow.getValue("value_count"));
        Assert.assertEquals(60.0, statsRow.getValue("value_sum"));
        Assert.assertEquals(20.0, statsRow.getValue("value_avg"));
    }
}
