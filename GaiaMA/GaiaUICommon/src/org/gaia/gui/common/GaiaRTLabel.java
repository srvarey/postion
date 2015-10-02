/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.util.Locale;
import javax.swing.JLabel;
import org.gaia.dao.jade.GaiaAgentController;
import org.gaia.dao.jade.IRefreshableWindow;
import org.gaia.dao.jade.RealTimeSubscriberAgent;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.MarketQuoteObservable;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;

/**
 *
 * @author Jawhar Kamoun
 */
public class GaiaRTLabel extends JLabel implements IRefreshableWindow {

    private AbstractObservable observable;
    private static final Format pointdecimalFormat = new DecimalFormat("#,##0.0000", new DecimalFormatSymbols(Locale.US));
    private RealTimeSubscriberAgent myAgent;
    private BigDecimal valueMultiplicator=BigDecimal.ONE;
    private Product product;

    public void setObservable(AbstractObservable _observable) {
        changeRTQuoteSubscription(observable,_observable);
        observable = _observable;
    }

    public GaiaRTLabel() {
        super();
    }

    public void setProduct(Product product){
        this.product=product;
    }

    @Override
    public void refresh(Object obj) {
        IObservable _observable = (IObservable) obj;
        Object[] coord = new Object[1];
        if(_observable instanceof MarketQuoteObservable){
            coord[0] = MarketQuote.CLOSE;
        }else if (product!=null){
            coord[0] = product.getMaturityDate();
        }
        if (coord[0]!=null){
            BigDecimal value=_observable.getObservableValue(coord);
            if (value!=null){
                value=value.multiply(valueMultiplicator);
                setText(pointdecimalFormat.format(value));
            }
        }
    }

    public void startRTQuote() {
        GaiaAgentController reportAgentController = GaiaAgentController.getInstance();
        myAgent = (RealTimeSubscriberAgent) reportAgentController.createLocalAgent(RealTimeSubscriberAgent.class);
        myAgent.setUI(this);
        if (observable != null && observable.getProductId()!=null) {
            myAgent.addBehaviour(myAgent.new SubscribeToRealTime(observable));
        }
    }

    private void changeRTQuoteSubscription(AbstractObservable oldObservable,AbstractObservable newObservable ) {
        if (myAgent != null) {
            if (oldObservable != null){
                myAgent.sendStopRealTimeRequest(oldObservable.getProductId());
            }
            if (newObservable!=null){
                myAgent.addBehaviour(myAgent.new SubscribeToRealTime(newObservable));
            }
        }
    }

    public void stopRTQuoteSubscription() {
        if (myAgent!=null && observable!=null){
            myAgent.sendStopRealTimeRequest(observable.getProductId());
            myAgent.doDelete();
        }
    }

    public void setValueMultiplicator(BigDecimal valueMultiplicator) {
        this.valueMultiplicator = valueMultiplicator;
    }

    @Override
    @SuppressWarnings("FinalizeDeclaration")
    protected void finalize() throws Throwable {
        stopRTQuoteSubscription();
        super.finalize();
    }



}
