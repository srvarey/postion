 /**
 * Copyright (C) 2013 Gaia Transparence
 * Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.02 at 07:44:16 PM CEST 
//


package org.gaia.markit.generated;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Pairvalidto_QNAME = new QName("", "pairvalidto");
    private final static QName _Region_QNAME = new QName("", "region");
    private final static QName _Ccy_QNAME = new QName("", "ccy");
    private final static QName _Series_QNAME = new QName("", "series");
    private final static QName _Refentityname_QNAME = new QName("", "refentityname");
    private final static QName _Maturity_QNAME = new QName("", "maturity");
    private final static QName _Refentityred_QNAME = new QName("", "refentityred");
    private final static QName _Weight_QNAME = new QName("", "weight");
    private final static QName _Tier_QNAME = new QName("", "tier");
    private final static QName _Frequency_QNAME = new QName("", "frequency");
    private final static QName _Originalisin_QNAME = new QName("", "originalisin");
    private final static QName _Type_QNAME = new QName("", "type");
    private final static QName _Date_QNAME = new QName("", "date");
    private final static QName _Docclause_QNAME = new QName("", "docclause");
    private final static QName _Effective_QNAME = new QName("", "effective");
    private final static QName _Indexname_QNAME = new QName("", "indexname");
    private final static QName _Issuerfromprospectus_QNAME = new QName("", "issuerfromprospectus");
    private final static QName _Version_QNAME = new QName("", "version");
    private final static QName _Guarantorfromprospectus_QNAME = new QName("", "guarantorfromprospectus");
    private final static QName _Ticker_QNAME = new QName("", "ticker");
    private final static QName _Ispreferred_QNAME = new QName("", "ispreferred");
    private final static QName _Event_QNAME = new QName("", "event");
    private final static QName _Family_QNAME = new QName("", "family");
    private final static QName _Name_QNAME = new QName("", "name");
    private final static QName _Fixedrate_QNAME = new QName("", "fixedrate");
    private final static QName _Annex_QNAME = new QName("", "annex");
    private final static QName _Indexfactor_QNAME = new QName("", "indexfactor");
    private final static QName _Coupon_QNAME = new QName("", "coupon");
    private final static QName _Tradeid_QNAME = new QName("", "tradeid");
    private final static QName _Period_QNAME = new QName("", "period");
    private final static QName _Isconvert_QNAME = new QName("", "isconvert");
    private final static QName _Coupontype_QNAME = new QName("", "coupontype");
    private final static QName _Country_QNAME = new QName("", "country");
    private final static QName _Recorddate_QNAME = new QName("", "recorddate");
    private final static QName _Prospname_QNAME = new QName("", "prospname");
    private final static QName _Isperp_QNAME = new QName("", "isperp");
    private final static QName _Red_QNAME = new QName("", "red");
    private final static QName _Pairvalidfrom_QNAME = new QName("", "pairvalidfrom");
    private final static QName _Pairiscurrent_QNAME = new QName("", "pairiscurrent");
    private final static QName _Firstpayment_QNAME = new QName("", "firstpayment");
    private final static QName _Validto_QNAME = new QName("", "validto");
    private final static QName _Validfrom_QNAME = new QName("", "validfrom");
    private final static QName _Cusip_QNAME = new QName("", "cusip");
    private final static QName _Notes_QNAME = new QName("", "notes");
    private final static QName _Isin_QNAME = new QName("", "isin");
    private final static QName _Pairred_QNAME = new QName("", "pairred");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Data }
     * 
     */
    public Data createData() {
        return new Data();
    }

    /**
     * Create an instance of {@link HeaderType }
     * 
     */
    public HeaderType createHeaderType() {
        return new HeaderType();
    }

    /**
     * Create an instance of {@link IndexType }
     * 
     */
    public IndexType createIndexType() {
        return new IndexType();
    }

    /**
     * Create an instance of {@link RefentityType }
     * 
     */
    public RefentityType createRefentityType() {
        return new RefentityType();
    }

    /**
     * Create an instance of {@link IssuerType }
     * 
     */
    public IssuerType createIssuerType() {
        return new IssuerType();
    }

    /**
     * Create an instance of {@link TermsType }
     * 
     */
    public TermsType createTermsType() {
        return new TermsType();
    }

    /**
     * Create an instance of {@link GuarantorType }
     * 
     */
    public GuarantorType createGuarantorType() {
        return new GuarantorType();
    }

    /**
     * Create an instance of {@link BondType }
     * 
     */
    public BondType createBondType() {
        return new BondType();
    }

    /**
     * Create an instance of {@link ComponentType }
     * 
     */
    public ComponentType createComponentType() {
        return new ComponentType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pairvalidto")
    public JAXBElement<String> createPairvalidto(String value) {
        return new JAXBElement<String>(_Pairvalidto_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "region")
    public JAXBElement<String> createRegion(String value) {
        return new JAXBElement<String>(_Region_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ccy")
    public JAXBElement<String> createCcy(String value) {
        return new JAXBElement<String>(_Ccy_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "series")
    public JAXBElement<Integer> createSeries(Integer value) {
        return new JAXBElement<Integer>(_Series_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "refentityname")
    public JAXBElement<String> createRefentityname(String value) {
        return new JAXBElement<String>(_Refentityname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "maturity")
    public JAXBElement<XMLGregorianCalendar> createMaturity(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Maturity_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "refentityred")
    public JAXBElement<String> createRefentityred(String value) {
        return new JAXBElement<String>(_Refentityred_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "weight")
    public JAXBElement<BigDecimal> createWeight(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Weight_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "tier")
    public JAXBElement<String> createTier(String value) {
        return new JAXBElement<String>(_Tier_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "frequency")
    public JAXBElement<String> createFrequency(String value) {
        return new JAXBElement<String>(_Frequency_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "originalisin")
    public JAXBElement<String> createOriginalisin(String value) {
        return new JAXBElement<String>(_Originalisin_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "type")
    public JAXBElement<String> createType(String value) {
        return new JAXBElement<String>(_Type_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "date")
    public JAXBElement<XMLGregorianCalendar> createDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Date_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "docclause")
    public JAXBElement<String> createDocclause(String value) {
        return new JAXBElement<String>(_Docclause_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "effective")
    public JAXBElement<XMLGregorianCalendar> createEffective(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Effective_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "indexname")
    public JAXBElement<String> createIndexname(String value) {
        return new JAXBElement<String>(_Indexname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "issuerfromprospectus")
    public JAXBElement<String> createIssuerfromprospectus(String value) {
        return new JAXBElement<String>(_Issuerfromprospectus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "version")
    public JAXBElement<String> createVersion(String value) {
        return new JAXBElement<String>(_Version_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "guarantorfromprospectus")
    public JAXBElement<String> createGuarantorfromprospectus(String value) {
        return new JAXBElement<String>(_Guarantorfromprospectus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ticker")
    public JAXBElement<String> createTicker(String value) {
        return new JAXBElement<String>(_Ticker_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ispreferred")
    public JAXBElement<Boolean> createIspreferred(Boolean value) {
        return new JAXBElement<Boolean>(_Ispreferred_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "event")
    public JAXBElement<String> createEvent(String value) {
        return new JAXBElement<String>(_Event_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "family")
    public JAXBElement<String> createFamily(String value) {
        return new JAXBElement<String>(_Family_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "name")
    public JAXBElement<String> createName(String value) {
        return new JAXBElement<String>(_Name_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fixedrate")
    public JAXBElement<BigDecimal> createFixedrate(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Fixedrate_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "annex")
    public JAXBElement<XMLGregorianCalendar> createAnnex(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Annex_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "indexfactor")
    public JAXBElement<BigDecimal> createIndexfactor(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Indexfactor_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "coupon")
    public JAXBElement<BigDecimal> createCoupon(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Coupon_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "tradeid")
    public JAXBElement<String> createTradeid(String value) {
        return new JAXBElement<String>(_Tradeid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "period")
    public JAXBElement<String> createPeriod(String value) {
        return new JAXBElement<String>(_Period_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isconvert")
    public JAXBElement<Boolean> createIsconvert(Boolean value) {
        return new JAXBElement<Boolean>(_Isconvert_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "coupontype")
    public JAXBElement<String> createCoupontype(String value) {
        return new JAXBElement<String>(_Coupontype_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "country")
    public JAXBElement<String> createCountry(String value) {
        return new JAXBElement<String>(_Country_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "recorddate")
    public JAXBElement<XMLGregorianCalendar> createRecorddate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Recorddate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "prospname")
    public JAXBElement<String> createProspname(String value) {
        return new JAXBElement<String>(_Prospname_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isperp")
    public JAXBElement<Boolean> createIsperp(Boolean value) {
        return new JAXBElement<Boolean>(_Isperp_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "red")
    public JAXBElement<String> createRed(String value) {
        return new JAXBElement<String>(_Red_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pairvalidfrom")
    public JAXBElement<String> createPairvalidfrom(String value) {
        return new JAXBElement<String>(_Pairvalidfrom_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pairiscurrent")
    public JAXBElement<String> createPairiscurrent(String value) {
        return new JAXBElement<String>(_Pairiscurrent_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "firstpayment")
    public JAXBElement<XMLGregorianCalendar> createFirstpayment(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Firstpayment_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "validto")
    public JAXBElement<XMLGregorianCalendar> createValidto(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Validto_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "validfrom")
    public JAXBElement<XMLGregorianCalendar> createValidfrom(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Validfrom_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "cusip")
    public JAXBElement<String> createCusip(String value) {
        return new JAXBElement<String>(_Cusip_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "notes")
    public JAXBElement<String> createNotes(String value) {
        return new JAXBElement<String>(_Notes_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "isin")
    public JAXBElement<String> createIsin(String value) {
        return new JAXBElement<String>(_Isin_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pairred")
    public JAXBElement<String> createPairred(String value) {
        return new JAXBElement<String>(_Pairred_QNAME, String.class, null, value);
    }

}
