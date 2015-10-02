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
package org.gaia.core.agents;

import jade.core.Agent;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

/**
 * Provides a container instance in order to configure an agents start
 * configuration. This class is used in the simulation setup of <b>Agent.GUI</b>
 *
 *
 */
public class AgentClassElement4SimStart {

    private static final Logger logger = Logger.getLogger(AgentClassElement4SimStart.class);
    /**
     *
     * The df.
     */
    @XmlTransient
    private DecimalFormat df = new DecimalFormat("00000");
    /**
     * The agent class.
     */
    private Class<? extends Agent> agentClass = null;
    /**
     * The postion no.
     */
    @XmlElement(name = "postionNo")
    private Integer postionNo = 0;
    /**
     * The list membership.
     */
    @XmlElement(name = "listMembership")
    private String listMembership = null;
    /**
     * The agent class reference.
     */
    @XmlElement(name = "agentClassReference")
    private String agentClassReference = null;
    /**
     * The start as name.
     */
    @XmlElement(name = "startAsName")
    private String startAsName = "";
    /**
     * The start arguments.
     */
    @XmlElementWrapper(name = "startArguments")
    @XmlElement(name = "argument")
    private String[] startArguments = null;


    /**
     * Constructor of this class by using the Class which extends Agent.
     *
     * @param agentClass the agent class
     * @param listMembership the list membership
     */
    public AgentClassElement4SimStart(Class<? extends Agent> agentClass, String listMembership) {
        this.agentClass = agentClass;
        this.agentClassReference = this.agentClass.getName();
        this.listMembership = listMembership;
        this.setDefaultAgentName();
    }

    /**
     * Constructor of this class by using an AgentClassElement-Object.
     *
     * @param agentClassElement the agent class element
     * @param listMembership the list membership
     */
    public AgentClassElement4SimStart(AgentClassElement agentClassElement, String listMembership) {
        this.agentClass = agentClassElement.getElementClass();
        this.agentClassReference = this.agentClass.getName();
        this.listMembership = listMembership;
        this.setDefaultAgentName();
    }

    /**
     * Sets a default name for the executed Agent in the Simulation-Experiment.
     */
    private void setDefaultAgentName() {

        String StartAs = agentClass.getName();
        StartAs = StartAs.substring(StartAs.lastIndexOf(".") + 1);
        String RegExp = "[A-Z]";
        String StartAsNew = "";
        for (int i = 0; i < StartAs.length(); i++) {
            String SngChar = "" + StartAs.charAt(i);
            if (SngChar.matches(RegExp) == true) {
                StartAsNew = StartAsNew + SngChar;
                if (i < StartAs.length()) {
                    String SngCharN = "" + StartAs.charAt(i + 1);
                    if (SngCharN.matches(RegExp) == false) {
                        StartAsNew = StartAsNew + SngCharN;
                    }
                }
                // ---------------------------------------------
            }
        }
        if (StartAsNew.length() >= 4) {
            StartAs = StartAsNew;
        }
        startAsName = StartAs;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings("unchecked")
    @Override
    public String toString() {
        if (agentClass == null) {
            try {
                agentClass = (Class<? extends Agent>) Class.forName(agentClassReference);
            } catch (ClassNotFoundException exeption) {
                logger.error("Could not find agent class '" + agentClassReference + "'!");
            }
        }
        if (agentClass == null) {
            return "NotFound: " + df.format(postionNo) + ": " + startAsName + " [" + agentClassReference + "]";
        } else {
            return df.format(postionNo) + ": " + startAsName + " [" + agentClass.getName() + "]";
        }

    }

    /**
     * Returns the class of the current Agent.
     *
     * @return the Class<? extends Agent> instance of the agent
     */
    public Class<? extends Agent> getElementClass() {
        return agentClass;
    }

    /**
     * Returns the position of the currently configured agent in the current
     * list.
     *
     * @return Integer position number
     */
    @XmlTransient
    public Integer getPostionNo() {
        return postionNo;
    }

    /**
     * Sets the position number of the current agent.
     *
     * @param postionNo the postionNo to set
     */
    public void setPostionNo(Integer postionNo) {
        this.postionNo = postionNo;
    }

    /**
     * Returns the listType this entry belongs to.
     *
     * @return the listType
     */
    @XmlTransient
    public String getListMembership() {
        return listMembership;
    }

    /**
     * Sets the type of the list this entry belongs to.
     *
     * @param listMembership the listType to set
     */
    public void setListMembership(String listMembership) {
        this.listMembership = listMembership;
    }

    /**
     * Returns the local name of the agent like ((AID)agentAID).getLocalname()
     *
     * @return the startAsName
     */
    @XmlTransient
    public String getStartAsName() {
        return startAsName;
    }

    /**
     * Here the local name for the agent can be set similar to
     * ((AID)agentAID).getLocalname()
     *
     * @param startAsName the startAsName to set
     */
    public void setStartAsName(String startAsName) {
        this.startAsName = startAsName;
    }

    /**
     * This method returns the class reference of the agent.
     *
     * @return String the agentClassReference
     */
    @XmlTransient
    public String getAgentClassReference() {
        return agentClassReference;
    }

    /**
     * Here the class reference of the agent can be set.
     *
     * @param agentClassReference the agentClassReference to set
     */
    public void setAgentClassReference(String agentClassReference) {
        this.agentClassReference = agentClassReference;
    }

    /**
     * This method will return the serialized start arguments of the current
     * agent. Internally these arguments will be kept as Base64 encoded Strings
     * in order to store this configuration also in the SimulationSetup
     *
     * @return the startInstances
     * @see agentgui.core.sim.setup.SimulationSetup
     */
    @XmlTransient
    public String[] getStartArguments() {

        if (this.startArguments == null) {
            return null;
        }

        String[] startArgumentsDecoded = new String[this.startArguments.length];
        String decodedArgument;
        try {
            for (int i = 0; i < startArguments.length; i++) {
                decodedArgument = new String(Base64.decodeBase64(startArguments[i].getBytes()), "UTF8");
                startArgumentsDecoded[i] = decodedArgument;
            }

        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        return startArgumentsDecoded;
    }

    /**
     * This method can be used in order to save the start arguments of the
     * current agent. Internally these arguments will be kept as Base64 encoded
     * Strings in order to store this configuration also in the SimulationSetup
     *
     * @param startArguments the startInstances to set for the agent start up
     * @see agentgui.core.sim.setup.SimulationSetup
     */
    public void setStartArguments(String[] startArguments) {

        if (startArguments.length == 0) {
            this.startArguments = null;
            return;
        }
        String[] startArgumentsEncoded = new String[startArguments.length];
        String encodedArgument;
        try {
            for (int i = 0; i < startArguments.length; i++) {
                encodedArgument = new String(Base64.encodeBase64(startArguments[i].getBytes("UTF8")));
                startArgumentsEncoded[i] = encodedArgument;
            }

        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        this.startArguments = startArgumentsEncoded;
    }
}
