package basic.of.javastudy.banksystem.exporter;

import basic.of.javastudy.banksystem.SummaryStatics;

public class HtmlExporter implements Exporter{
    @Override
    public String export(SummaryStatics summaryStatics) {

        String result = "<!doctype html>";
        result += "<html lang='en'>";
        result += "<head><title>Bank Transaction Report</title></head>";
        result += "<body>";
        result += "<ul>";
        result += "<li><strong>The sum is</strong> : " + summaryStatics.getSum() + "</li>";
        result += "<li><strong>The average is</strong> : " + summaryStatics.getAverage() + "</li>";
        result += "<li><strong>The max is</strong> : " + summaryStatics.getMax() + "</li>";
        result += "<li><strong>The min is</strong> : " + summaryStatics.getMin() + "</li>";
        result += "</ul>";
        result += "</body>";
        result += "</html>";
        return result;
    }
}
