//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.17 à 01:35:40 PM CET 
//


package org.gaia.fpml.fpml_5.recordkeeping;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DeliveryDatesEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="DeliveryDatesEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="CalculationPeriod"/>
 *     &lt;enumeration value="FirstNearby"/>
 *     &lt;enumeration value="SecondNearby"/>
 *     &lt;enumeration value="ThirdNearby"/>
 *     &lt;enumeration value="FourthNearby"/>
 *     &lt;enumeration value="FifthNearby"/>
 *     &lt;enumeration value="SixthNearby"/>
 *     &lt;enumeration value="SeventhNearby"/>
 *     &lt;enumeration value="EighthNearby"/>
 *     &lt;enumeration value="NinthNearby"/>
 *     &lt;enumeration value="TenthNearby"/>
 *     &lt;enumeration value="EleventhNearby"/>
 *     &lt;enumeration value="TwelfthNearby"/>
 *     &lt;enumeration value="ThirteenthNearby"/>
 *     &lt;enumeration value="FourteenthNearby"/>
 *     &lt;enumeration value="Spot"/>
 *     &lt;enumeration value="FirstNearbyWeek"/>
 *     &lt;enumeration value="SecondNearbyWeek"/>
 *     &lt;enumeration value="ThirdNearbyWeek"/>
 *     &lt;enumeration value="FourthNearbyWeek"/>
 *     &lt;enumeration value="FifthNearbyWeek"/>
 *     &lt;enumeration value="SixthNearbyWeek"/>
 *     &lt;enumeration value="SeventhNearbyWeek"/>
 *     &lt;enumeration value="EighthNearbyWeek"/>
 *     &lt;enumeration value="NinthNearbyWeek"/>
 *     &lt;enumeration value="TenthNearbyWeek"/>
 *     &lt;enumeration value="EleventhNearbyWeek"/>
 *     &lt;enumeration value="TwelfthNearbyWeek"/>
 *     &lt;enumeration value="ThirteenthNearbyWeek"/>
 *     &lt;enumeration value="FourteenthNearbyWeek"/>
 *     &lt;enumeration value="FifteenthNearbyWeek"/>
 *     &lt;enumeration value="SixteenthNearbyWeek"/>
 *     &lt;enumeration value="SeventeenthNearbyWeek"/>
 *     &lt;enumeration value="EighteenthNearbyWeek"/>
 *     &lt;enumeration value="NineteenthNearbyWeek"/>
 *     &lt;enumeration value="TwentiethNearbyWeek"/>
 *     &lt;enumeration value="TwentyFirstNearbyWeek"/>
 *     &lt;enumeration value="TwentySecondNearbyWeek"/>
 *     &lt;enumeration value="TwentyThirdNearbyWeek"/>
 *     &lt;enumeration value="TwentyFourthearbyWeek"/>
 *     &lt;enumeration value="TwentyFifthNearbyWeek"/>
 *     &lt;enumeration value="TwentySixthNearbyWeek"/>
 *     &lt;enumeration value="TwentySeventhNearbyWeek"/>
 *     &lt;enumeration value="TwentyEighthNearbyWeek"/>
 *     &lt;enumeration value="TwentyNinthNearbyWeek"/>
 *     &lt;enumeration value="ThirtiethNearbyWeek"/>
 *     &lt;enumeration value="ThirtyFirstNearbyWeek"/>
 *     &lt;enumeration value="ThirtySecondNearbyWeek"/>
 *     &lt;enumeration value="ThirtyThirdNearbyWeek"/>
 *     &lt;enumeration value="ThirtyFourthNearbyWeek"/>
 *     &lt;enumeration value="ThirtyFifthNearbyWeek"/>
 *     &lt;enumeration value="ThirtySixthNearbyWeek"/>
 *     &lt;enumeration value="ThirtySeventhNearbyWeek"/>
 *     &lt;enumeration value="ThirtyEighthNearbyWeek"/>
 *     &lt;enumeration value="ThirtyNinthNearbyWeek"/>
 *     &lt;enumeration value="FortiethNearbyWeek"/>
 *     &lt;enumeration value="FortyFirstNearbyWeek"/>
 *     &lt;enumeration value="FortySecondNearbyWeek"/>
 *     &lt;enumeration value="FortyThirdNearbyWeek"/>
 *     &lt;enumeration value="FortyFourthNearbyWeek"/>
 *     &lt;enumeration value="FortyFifthNearbyWeek"/>
 *     &lt;enumeration value="FortySixthNearbyWeek"/>
 *     &lt;enumeration value="FortySeventhNearbyWeek"/>
 *     &lt;enumeration value="FortyEighthNearbyWeek"/>
 *     &lt;enumeration value="FortyNinthNearbyWeek"/>
 *     &lt;enumeration value="FiftiethNearbyWeek"/>
 *     &lt;enumeration value="FiftyFirstNearbyWeek"/>
 *     &lt;enumeration value="FiftySecondNearbyWeek"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DeliveryDatesEnum")
@XmlEnum
public enum DeliveryDatesEnum {


    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the futures contract that corresponds to the month and year of the Calculation Period. e.g. The JAN 09 contract when pricing in January '09 (In the case of contracts like Brent crude, this will mean that the contract expired in DEC 08.)
     * 
     */
    @XmlEnumValue("CalculationPeriod")
    CALCULATION_PERIOD("CalculationPeriod"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the First Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("FirstNearby")
    FIRST_NEARBY("FirstNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Second Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("SecondNearby")
    SECOND_NEARBY("SecondNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Third Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("ThirdNearby")
    THIRD_NEARBY("ThirdNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Fourth Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("FourthNearby")
    FOURTH_NEARBY("FourthNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Fifth Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("FifthNearby")
    FIFTH_NEARBY("FifthNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Sixth Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("SixthNearby")
    SIXTH_NEARBY("SixthNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Seventh Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("SeventhNearby")
    SEVENTH_NEARBY("SeventhNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Eighth Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("EighthNearby")
    EIGHTH_NEARBY("EighthNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Ninth Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("NinthNearby")
    NINTH_NEARBY("NinthNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Tenth Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("TenthNearby")
    TENTH_NEARBY("TenthNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Eleventh Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("EleventhNearby")
    ELEVENTH_NEARBY("EleventhNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Twelfth Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("TwelfthNearby")
    TWELFTH_NEARBY("TwelfthNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Thirteenth Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("ThirteenthNearby")
    THIRTEENTH_NEARBY("ThirteenthNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the month of expiration of the Fourteenth Nearby Month futures contract.
     * 
     */
    @XmlEnumValue("FourteenthNearby")
    FOURTEENTH_NEARBY("FourteenthNearby"),

    /**
     * The Delivery Date of the underlying Commodity shall be the Spot date.
     * 
     */
    @XmlEnumValue("Spot")
    SPOT("Spot"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the First Nearby Week.
     * 
     */
    @XmlEnumValue("FirstNearbyWeek")
    FIRST_NEARBY_WEEK("FirstNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Second Nearby Week.
     * 
     */
    @XmlEnumValue("SecondNearbyWeek")
    SECOND_NEARBY_WEEK("SecondNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Third Nearby Week.
     * 
     */
    @XmlEnumValue("ThirdNearbyWeek")
    THIRD_NEARBY_WEEK("ThirdNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Fourth Nearby Week.
     * 
     */
    @XmlEnumValue("FourthNearbyWeek")
    FOURTH_NEARBY_WEEK("FourthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Fifth Nearby Week.
     * 
     */
    @XmlEnumValue("FifthNearbyWeek")
    FIFTH_NEARBY_WEEK("FifthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Sixth Nearby Week.
     * 
     */
    @XmlEnumValue("SixthNearbyWeek")
    SIXTH_NEARBY_WEEK("SixthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Seventh Nearby Week.
     * 
     */
    @XmlEnumValue("SeventhNearbyWeek")
    SEVENTH_NEARBY_WEEK("SeventhNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Eighth Nearby Week.
     * 
     */
    @XmlEnumValue("EighthNearbyWeek")
    EIGHTH_NEARBY_WEEK("EighthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Ninth Nearby Week.
     * 
     */
    @XmlEnumValue("NinthNearbyWeek")
    NINTH_NEARBY_WEEK("NinthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Tenth Nearby Week.
     * 
     */
    @XmlEnumValue("TenthNearbyWeek")
    TENTH_NEARBY_WEEK("TenthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Eleventh Nearby Week.
     * 
     */
    @XmlEnumValue("EleventhNearbyWeek")
    ELEVENTH_NEARBY_WEEK("EleventhNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twelfth Nearby Week.
     * 
     */
    @XmlEnumValue("TwelfthNearbyWeek")
    TWELFTH_NEARBY_WEEK("TwelfthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirteenth Nearby Week.
     * 
     */
    @XmlEnumValue("ThirteenthNearbyWeek")
    THIRTEENTH_NEARBY_WEEK("ThirteenthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Fourteenth Nearby Week.
     * 
     */
    @XmlEnumValue("FourteenthNearbyWeek")
    FOURTEENTH_NEARBY_WEEK("FourteenthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Fifteenth Nearby Week.
     * 
     */
    @XmlEnumValue("FifteenthNearbyWeek")
    FIFTEENTH_NEARBY_WEEK("FifteenthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Sixteenth Nearby Week.
     * 
     */
    @XmlEnumValue("SixteenthNearbyWeek")
    SIXTEENTH_NEARBY_WEEK("SixteenthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Seventeenth Nearby Week.
     * 
     */
    @XmlEnumValue("SeventeenthNearbyWeek")
    SEVENTEENTH_NEARBY_WEEK("SeventeenthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Eighteenth Nearby Week.
     * 
     */
    @XmlEnumValue("EighteenthNearbyWeek")
    EIGHTEENTH_NEARBY_WEEK("EighteenthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Nineteenth Nearby Week.
     * 
     */
    @XmlEnumValue("NineteenthNearbyWeek")
    NINETEENTH_NEARBY_WEEK("NineteenthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twentieth Nearby Week.
     * 
     */
    @XmlEnumValue("TwentiethNearbyWeek")
    TWENTIETH_NEARBY_WEEK("TwentiethNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twenty First Nearby Week.
     * 
     */
    @XmlEnumValue("TwentyFirstNearbyWeek")
    TWENTY_FIRST_NEARBY_WEEK("TwentyFirstNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twenty Second Nearby Week.
     * 
     */
    @XmlEnumValue("TwentySecondNearbyWeek")
    TWENTY_SECOND_NEARBY_WEEK("TwentySecondNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twenty Third Nearby Week.
     * 
     */
    @XmlEnumValue("TwentyThirdNearbyWeek")
    TWENTY_THIRD_NEARBY_WEEK("TwentyThirdNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twenty Fourth Nearby Week.
     * 
     */
    @XmlEnumValue("TwentyFourthearbyWeek")
    TWENTY_FOURTHEARBY_WEEK("TwentyFourthearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twenty Fifth Nearby Week.
     * 
     */
    @XmlEnumValue("TwentyFifthNearbyWeek")
    TWENTY_FIFTH_NEARBY_WEEK("TwentyFifthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twenty Sixth Nearby Week.
     * 
     */
    @XmlEnumValue("TwentySixthNearbyWeek")
    TWENTY_SIXTH_NEARBY_WEEK("TwentySixthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twenty Seventh Nearby Week.
     * 
     */
    @XmlEnumValue("TwentySeventhNearbyWeek")
    TWENTY_SEVENTH_NEARBY_WEEK("TwentySeventhNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twenty Eighth Nearby Week.
     * 
     */
    @XmlEnumValue("TwentyEighthNearbyWeek")
    TWENTY_EIGHTH_NEARBY_WEEK("TwentyEighthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Twenty Ninth Nearby Week.
     * 
     */
    @XmlEnumValue("TwentyNinthNearbyWeek")
    TWENTY_NINTH_NEARBY_WEEK("TwentyNinthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirtieth Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtiethNearbyWeek")
    THIRTIETH_NEARBY_WEEK("ThirtiethNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirty First Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtyFirstNearbyWeek")
    THIRTY_FIRST_NEARBY_WEEK("ThirtyFirstNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirty Second Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtySecondNearbyWeek")
    THIRTY_SECOND_NEARBY_WEEK("ThirtySecondNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirty Third Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtyThirdNearbyWeek")
    THIRTY_THIRD_NEARBY_WEEK("ThirtyThirdNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirty Fourth Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtyFourthNearbyWeek")
    THIRTY_FOURTH_NEARBY_WEEK("ThirtyFourthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirty Fifth Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtyFifthNearbyWeek")
    THIRTY_FIFTH_NEARBY_WEEK("ThirtyFifthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirty Sixth Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtySixthNearbyWeek")
    THIRTY_SIXTH_NEARBY_WEEK("ThirtySixthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirty Seventh Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtySeventhNearbyWeek")
    THIRTY_SEVENTH_NEARBY_WEEK("ThirtySeventhNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirty Eighth Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtyEighthNearbyWeek")
    THIRTY_EIGHTH_NEARBY_WEEK("ThirtyEighthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Thirty Ninth Nearby Week.
     * 
     */
    @XmlEnumValue("ThirtyNinthNearbyWeek")
    THIRTY_NINTH_NEARBY_WEEK("ThirtyNinthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Fortieth Nearby Week.
     * 
     */
    @XmlEnumValue("FortiethNearbyWeek")
    FORTIETH_NEARBY_WEEK("FortiethNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Forty First Nearby Week.
     * 
     */
    @XmlEnumValue("FortyFirstNearbyWeek")
    FORTY_FIRST_NEARBY_WEEK("FortyFirstNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Forty Second Nearby Week.
     * 
     */
    @XmlEnumValue("FortySecondNearbyWeek")
    FORTY_SECOND_NEARBY_WEEK("FortySecondNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Forty Third Nearby Week.
     * 
     */
    @XmlEnumValue("FortyThirdNearbyWeek")
    FORTY_THIRD_NEARBY_WEEK("FortyThirdNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Forty Fourth Nearby Week.
     * 
     */
    @XmlEnumValue("FortyFourthNearbyWeek")
    FORTY_FOURTH_NEARBY_WEEK("FortyFourthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Forty Fifth Nearby Week.
     * 
     */
    @XmlEnumValue("FortyFifthNearbyWeek")
    FORTY_FIFTH_NEARBY_WEEK("FortyFifthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Forty Sixth Nearby Week.
     * 
     */
    @XmlEnumValue("FortySixthNearbyWeek")
    FORTY_SIXTH_NEARBY_WEEK("FortySixthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Forty Seventh Nearby Week.
     * 
     */
    @XmlEnumValue("FortySeventhNearbyWeek")
    FORTY_SEVENTH_NEARBY_WEEK("FortySeventhNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Forty Eighth Nearby Week.
     * 
     */
    @XmlEnumValue("FortyEighthNearbyWeek")
    FORTY_EIGHTH_NEARBY_WEEK("FortyEighthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Forty Ninth Nearby Week.
     * 
     */
    @XmlEnumValue("FortyNinthNearbyWeek")
    FORTY_NINTH_NEARBY_WEEK("FortyNinthNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Fiftieth Nearby Week.
     * 
     */
    @XmlEnumValue("FiftiethNearbyWeek")
    FIFTIETH_NEARBY_WEEK("FiftiethNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Fifty First Nearby Week.
     * 
     */
    @XmlEnumValue("FiftyFirstNearbyWeek")
    FIFTY_FIRST_NEARBY_WEEK("FiftyFirstNearbyWeek"),

    /**
     * The Delivery Date of the underlying Commodity shall be during the Fifty Second Nearby Week.
     * 
     */
    @XmlEnumValue("FiftySecondNearbyWeek")
    FIFTY_SECOND_NEARBY_WEEK("FiftySecondNearbyWeek");
    private final String value;

    DeliveryDatesEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DeliveryDatesEnum fromValue(String v) {
        for (DeliveryDatesEnum c: DeliveryDatesEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
