package org.gaia.dao.reports.customColumns;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import org.gaia.dao.reports.ReportLine;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Trade;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;



/**
 *
 * @author Benjamin Frerejean
 */


public class EMIRTypeCustomColumnTest  extends AbstractTest{


    /**
     * Test of calculate method, of class EMIRTypeCustomColumn.
     */
    @Test
    public void testCalculate() {
        Trade trade=(Trade) HibernateUtil.getObjectWithQuery("from Trade t where t.tradeId=(select min(tradeId) from Trade)");
        TemplateColumnItem templateColumnItem = null;
        ReportLine line = new ReportLine();
        line.setLineData(trade);
        line.setId(trade.getId());
        Date valDate = DateUtils.getDate();
        Date startDate = null;
        String suffix = "";
        Map<String, BigDecimal> mapFXEnd = null;
        Map<String, BigDecimal> mapFXStart = null;
        String reportCurrency = "";
        BuySellCustomColumn instance = new BuySellCustomColumn();
        Object result = instance.calculate(null,templateColumnItem, line, valDate, startDate, suffix, mapFXEnd, mapFXStart, reportCurrency);
        assertNotNull("failed to calculte emir types custom column", result);
    }

}
