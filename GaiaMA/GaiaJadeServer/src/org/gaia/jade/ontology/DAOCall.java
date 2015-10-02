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
package org.gaia.jade.ontology;

import jade.content.AgentAction;
import java.io.Serializable;


/**
 *
 * @author Benjamin Frerejean
 */
public class DAOCall implements Serializable,AgentAction{

    private String className;
    private String methodName;
    private java.util.List<Serializable> arguments;
    private Boolean isAsynchrone;
    private Boolean isXMLSerialized;

    public DAOCall(){
        super();
    }

    public DAOCall(String className,String methodName, java.util.List<Serializable> arguments,Boolean isAsynchrone,Boolean isXMLSerialized){
        this.className=className;
        this.methodName=methodName;
        this.arguments=arguments;
        this.isAsynchrone=isAsynchrone;
        this.isXMLSerialized=isXMLSerialized;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public java.util.List<Serializable> getArguments() {
        return arguments;
    }

    public void setArguments(java.util.List<Serializable> arguments) {
        this.arguments = arguments;
    }

    public Boolean isAsynchrone() {
        return isAsynchrone;
    }

    public void setIsAsynchrone(Boolean isAsynchrone) {
        this.isAsynchrone = isAsynchrone;
    }

    public Boolean getIsXMLSerialized() {
        return isXMLSerialized;
    }

    public void setIsXMLSerialized(Boolean isXMLSerialized) {
        this.isXMLSerialized = isXMLSerialized;
    }

    public String toString(){
        return className+"."+methodName;
    }

}
