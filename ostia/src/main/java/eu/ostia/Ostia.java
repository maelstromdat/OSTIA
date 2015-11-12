package eu.ostia;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ostia {
    public static void main(String[] args) throws Exception {
        new Ostia();
    }

    public Ostia() throws Exception {
        FileInputStream in = new FileInputStream("examples/FocusedCrawler.java");

        CompilationUnit compilationUnit;
        try {
            compilationUnit = JavaParser.parse(in);
        } finally {
            in.close();
        }
        MethodVisitor visitor = new MethodVisitor();
        visitor.visit(compilationUnit, null);
        visitor.printTopology();
    }

    private class Link {
        String _from;
        String _to;
        String _label;

        Link(String from, String to, String label){
            _from = from;
            _to = to;
            _label = label;
        }
    }

    private class MethodVisitor extends VoidVisitorAdapter {
        DirectGraph<String> _topology;

        MethodVisitor() {
            _topology = new DirectGraph<String>();
        }

        @Override
        public void visit(MethodCallExpr call, Object arg) {
            if (isSpout(call)) {
                String spoutName = getSpoutName(call);
                _topology.add(spoutName);
            } else if (isBolt(call)) {
                String parent = call.getParentNode().toString();
                ArrayList<Link> edges = getBoltLinks(parent);
                for (Link link : edges) {
                    _topology.add(link._from, link._to, link._label);
                }
            }
            super.visit(call, arg);
        }

        private String getSpoutName(MethodCallExpr methodCall) {
            Pattern string = Pattern.compile("\"[A-Za-z0-9]+\"");
            Matcher matcher = string.matcher(methodCall.toString());
            if (matcher.find()) {
                return matcher.group(0);
            } else {
                return null;
            }
        }

        private ArrayList<Link> getBoltLinks(String methodCall) {
            Pattern string = Pattern.compile("(Grouping|Bolt|Spout)\\(\"([A-Za-z0-9]+)\"");
            Matcher matcher = string.matcher(methodCall);

            ArrayList<String> sources = new ArrayList<String>();
            System.out.println("B===");
            System.out.println(methodCall);
            while (matcher.find()) {
                System.out.println("Group: " + matcher.group(0));
                System.out.println("Group: " + matcher.group(1));
                System.out.println("Group: " + matcher.group(2));
                sources.add(matcher.group(2));
                System.out.println("E===");
            }
            String mainVertex = sources.get(0);
            sources.remove(mainVertex);

            ArrayList<Link> edges = new ArrayList<Link>();
            for (String source : sources) {
                edges.add(new Link(source, mainVertex, ""));
            }
            /*
            string = Pattern.compile("[A-Za-z0-9]+Grouping");
            matcher = string.matcher(methodCall);
            ArrayList<String> tmp = new ArrayList<String>();
            while (matcher.find()) {
                tmp.add(matcher.group(0));
            }
            assert(tmp.size() == 1);
            ary.add(tmp.get(0));
            return ary;
            */
            return edges;
        }

        private boolean isSpout(MethodCallExpr methodCall) {
            String name = methodCall.getName();
            return name.contains("setSpout");
        }

        private boolean isBolt(MethodCallExpr methodCall) {
            String name = methodCall.getName();
            return name.contains("setBolt");
        }

        public void printTopology() {
            System.out.println(_topology);
        }
    }
}
