package dice.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.ClusterSummary;
import backtype.storm.generated.Nimbus;
import backtype.storm.generated.TopologySummary;
import backtype.storm.metric.LoggingMetricsConsumer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.StormSubmitter;
import backtype.storm.tuple.Fields;

public class HelloStorm {

    public static void main(String[] args) throws Exception {
        Config config = new Config();

        config.setDebug(true);
        config.setMaxSpoutPending(1);
        config.setMessageTimeoutSecs(30);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("spout", new MySpout());
        builder.setBolt("split", new SplitterBolt()).shuffleGrouping("spout");
        builder.setBolt("counter", new WordCounterBolt()).fieldsGrouping("split", new Fields("word"));
        builder.setBolt("Y", new WordCounterBolt()).fieldsGrouping("X", new Fields("word"));

        config.setNumWorkers(3);
        config.setNumAckers(3);
        StormSubmitter.submitTopologyWithProgressBar("HelloStorm", config, builder.createTopology());
    }
}
