package io.cdap.wrangler.codec;

import io.cdap.wrangler.api.Row;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AggregateStatsTest {
  @Test
  public void testAggregateStats() {
    List<Row> rows = Arrays.asList(
      new Row("size", "1MB").add("time", "1000ms"),
      new Row("size", "2MB").add("time", "2s")
    );

    AggregateStats directive = new AggregateStats();
    directive.initialize(null, new DummyArguments());
    List<Row> result = directive.execute(rows, null);

    Assert.assertEquals(1, result.size());
    Assert.assertEquals(3.0, (Double) result.get(0).getValue("total_size_mb"), 0.001);
    Assert.assertEquals(3.0, (Double) result.get(0).getValue("total_time_sec"), 0.001);
  }
}
