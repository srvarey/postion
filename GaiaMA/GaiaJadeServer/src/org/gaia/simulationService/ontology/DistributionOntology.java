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
package org.gaia.simulationService.ontology;

import jade.content.onto.*;
import jade.content.schema.*;


public class DistributionOntology extends jade.content.onto.Ontology  {
  //NAME
  public static final String ONTOLOGY_NAME = "AgentGUI-Distribution";

  private static Ontology theInstance = new DistributionOntology();
  public static Ontology getInstance() {
     return theInstance;
  }
   // VOCABULARY
    public static final String MASTERUPDATENOTE_UPDATEINFOURL="updateInfoURL";
    public static final String MASTERUPDATENOTE="MasterUpdateNote";
    public static final String SHOWMONITORGUI="ShowMonitorGUI";
    public static final String REGISTERRECEIPT="RegisterReceipt";
    public static final String SLAVEUNREGISTER="SlaveUnregister";
    public static final String SLAVETRIGGER_TRIGGERTIME="triggerTime";
    public static final String SLAVETRIGGER_SLAVELOAD="slaveLoad";
    public static final String SLAVETRIGGER_SLAVEBENCHMARKVALUE="slaveBenchmarkValue";
    public static final String SLAVETRIGGER="SlaveTrigger";
    public static final String SLAVEREGISTER_SLAVEADDRESS="slaveAddress";
    public static final String SLAVEREGISTER_SLAVEPERFORMANCE="slavePerformance";
    public static final String SLAVEREGISTER_SLAVETIME="slaveTime";
    public static final String SLAVEREGISTER_SLAVEVERSION="slaveVersion";
    public static final String SLAVEREGISTER_SLAVEOS="slaveOS";
    public static final String SLAVEREGISTER="SlaveRegister";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEADDRESS="remoteAddress";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEAGENTGUIVERSION="remoteAgentGuiVersion";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEOS="remoteOS";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEPERFORMANCE="remotePerformance";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTECONTAINERNAME="remoteContainerName";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEPID="remotePID";
    public static final String CLIENTREMOTECONTAINERREPLY_REMOTEBENCHMARKRESULT="remoteBenchmarkResult";
    public static final String CLIENTREMOTECONTAINERREPLY="ClientRemoteContainerReply";
    public static final String CLIENTREMOTECONTAINERREQUEST_REMOTECONFIG="RemoteConfig";
    public static final String CLIENTREMOTECONTAINERREQUEST="ClientRemoteContainerRequest";
    public static final String CLIENTREGISTER_CLIENTTIME="clientTime";
    public static final String CLIENTREGISTER_CLIENTADDRESS="clientAddress";
    public static final String CLIENTREGISTER_CLIENTOS="clientOS";
    public static final String CLIENTREGISTER_CLIENTPERFORMANCE="clientPerformance";
    public static final String CLIENTREGISTER_CLIENTVERSION="clientVersion";
    public static final String CLIENTREGISTER="ClientRegister";
    public static final String CLIENTTRIGGER_TRIGGERTIME="triggerTime";
    public static final String CLIENTTRIGGER_CLIENTLOAD="clientLoad";
    public static final String CLIENTTRIGGER_CLIENTBENCHMARKVALUE="clientBenchmarkValue";
    public static final String CLIENTTRIGGER="ClientTrigger";
    public static final String CLIENTUNREGISTER="ClientUnregister";
    public static final String PLATFORMADDRESS_HTTP4MTP="http4mtp";
    public static final String PLATFORMADDRESS_PORT="port";
    public static final String PLATFORMADDRESS_URL="url";
    public static final String PLATFORMADDRESS_IP="ip";
    public static final String PLATFORMADDRESS="PlatformAddress";
    public static final String REMOTECONTAINERCONFIG_JADEJARINCLUDELIST="jadeJarIncludeList";
    public static final String REMOTECONTAINERCONFIG_PREVENTUSAGEOFUSEDCOMPUTER="preventUsageOfUsedComputer";
    public static final String REMOTECONTAINERCONFIG_JADEHOST="jadeHost";
    public static final String REMOTECONTAINERCONFIG_JADEISREMOTECONTAINER="jadeIsRemoteContainer";
    public static final String REMOTECONTAINERCONFIG_JADESHOWGUI="jadeShowGUI";
    public static final String REMOTECONTAINERCONFIG_JADEPORT="jadePort";
    public static final String REMOTECONTAINERCONFIG_JADESERVICES="jadeServices";
    public static final String REMOTECONTAINERCONFIG_JVMMEMALLOCMAXIMUM="jvmMemAllocMaximum";
    public static final String REMOTECONTAINERCONFIG_JVMMEMALLOCINITIAL="jvmMemAllocInitial";
    public static final String REMOTECONTAINERCONFIG_JADECONTAINERNAME="jadeContainerName";
    public static final String REMOTECONTAINERCONFIG="RemoteContainerConfig";
    public static final String PLATFORMLOAD_LOADNOTHREADS="loadNoThreads";
    public static final String PLATFORMLOAD_LOADMEMORYJVM="loadMemoryJVM";
    public static final String PLATFORMLOAD_LOADCPU="loadCPU";
    public static final String PLATFORMLOAD_LOADEXCEEDED="loadExceeded";
    public static final String PLATFORMLOAD_LOADMEMORYSYSTEM="loadMemorySystem";
    public static final String PLATFORMLOAD="PlatformLoad";
    public static final String BENCHMARKRESULT_BENCHMARKVALUE="benchmarkValue";
    public static final String BENCHMARKRESULT="BenchmarkResult";
    public static final String PLATFORMTIME_TIMESTAMPASSTRING="TimeStampAsString";
    public static final String PLATFORMTIME="PlatformTime";
    public static final String PLATFORMPERFORMANCE_CPU_MODEL="cpu_model";
    public static final String PLATFORMPERFORMANCE_CPU_VENDOR="cpu_vendor";
    public static final String PLATFORMPERFORMANCE_CPU_SPEEDMHZ="cpu_speedMhz";
    public static final String PLATFORMPERFORMANCE_MEMORY_TOTALMB="memory_totalMB";
    public static final String PLATFORMPERFORMANCE_CPU_NUMBEROF="cpu_numberOf";
    public static final String PLATFORMPERFORMANCE="PlatformPerformance";
    public static final String OSINFO_OS_VERSION="os_version";
    public static final String OSINFO_OS_NAME="os_name";
    public static final String OSINFO_OS_ARCH="os_arch";
    public static final String OSINFO="OSInfo";
    public static final String DISPATCHER_REGISTER="DispatcherRegister";
    public static final String AGENTCREATION="AgentCreation";
    public static final String AGENTCREATIONNAME="agentName";
    public static final String AGENTCREATIONCLASSNAME="agentClassName";
    public static final String AGENTCREATIONADDNOSUFFIXTONAME="addNoSuffixToName";
    public static final String AGENTCREATIONARGS="args";
    public static final String OBJECTMAP="ObjectMap";
    public static final String OBJECTMAPIDS="ObjectMapIds";
    public static final String OBJECTMAPOBJECTS="ObjectMapObjects";
    public static final String PRICINGSETTING="PricingSetting";
    public static final String PRICINGSETTINGVALUATIONDATE="valuationDate";
    public static final String PRICINGSETTINGNAME="name";
    public static final String PRICINGSETTINGPRICINGENVIRONMENTID="pricingEnvironmentId";
    public static final String PRICINGSETTINGPRICINGSETTINGITEMCOLLECTION="pricingSettingItemCollection";
    public static final String PRICINGSETTINGPRICERSSETTINGCOLLECTION="pricersSettingCollection";
    public static final String PRICINGSETTINGITEM="PricingSettingItem";
    public static final String PRICINGSETTINGITEMPRCINGSETTINGITEMID="pricingSettingItemId";
    public static final String PRICINGSETTINGITEMITEMTYPE="itemType";
    public static final String PRICINGSETTINGITEMPRICINGFUNCTION="pricingFunction";
    public static final String PRICINGSETTINGITEMCURRENCY="currency";
    public static final String PRICINGSETTINGITEMPRODUCTTYPE="productType";
    public static final String PRICINGSETTINGITEMTRADEFILTERNAME="tradeFilterName";
    public static final String PRICINGSETTINGITEMPRODUCTUNDERLYINGID="productUnderlyingId";
    public static final String PRICINGSETTINGITEMITEMVALUE="itemValue";
    public static final String PRICINGSETTINGITEMITEMVALUEID="itemValueId";
    public static final String PRICINGSETTINGMEASURES2D="Measures2D";
    public static final String PRICINGSETTINGLISTIDS2D="ListIds2D";
    public static final String PRICINGSETTINGISREALTIME="isRealTime";
    public static final String PRICERSSETTING="PricersSetting";
    public static final String PRICERSSETTINGPRICERSSETTINGID="pricersSettingId";
    public static final String PRICERSSETTINGPRODUCTTYPE="productType";
    public static final String PRICERSSETTINGTRADEFILTERNAME="tradeFilterName";
    public static final String PRICERSSETTINGMEASUREGROUP="measureGroup";
    public static final String PRICERSSETTINGPRICER="pricer";
    public static final String PRICINGAGENTCREATION="PricingAgentCreation";
    public static final String PRICINGAGENTCREATIONNAME="agentName";
    public static final String PRICINGAGENTCREATIONCLASSNAME="agentClassName";
    public static final String PRICINGAGENTCREATIONARGS="args";
    public static final String PRICINGAGENTCREATIONPRICINGSETTING="pricingSetting";
    public static final String DAOCALL="DAOCall";
    public static final String DAOCALLCLASSNAME="className";
    public static final String DAOCALLMETHODNAME="methodName";
    public static final String DAOCALLARGUMENTTYPENAMES="argumentTypeNames";
    public static final String DAOCALLARGUMENTS="arguments";
    public static final String DAOCALLISASYNCHRONE="isAsynchrone";
    public static final String REPORTOBJECTNOTIFICATION="ReportObjectNotification";
    public static final String REPORTOBJECTNOTIFICATIONOBJECTTYPE="objectType";
    public static final String REPORTOBJECTNOTIFICATIONID="id";
    public static final String REPORTOBJECTNOTIFICATIONISNEW="isNew";
    public static final String REPORTOBJECTNOTIFICATIONFROMDATE="fromDate";




  /**
   * Constructor
  */
  private DistributionOntology(){
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try {

    // adding Concept(s)
    ConceptSchema osInfoSchema = new ConceptSchema(OSINFO);
    add(osInfoSchema, org.gaia.simulationService.ontology.OSInfo.class);
    ConceptSchema platformPerformanceSchema = new ConceptSchema(PLATFORMPERFORMANCE);
    add(platformPerformanceSchema, org.gaia.simulationService.ontology.PlatformPerformance.class);
    ConceptSchema platformTimeSchema = new ConceptSchema(PLATFORMTIME);
    add(platformTimeSchema, org.gaia.simulationService.ontology.PlatformTime.class);
    ConceptSchema benchmarkResultSchema = new ConceptSchema(BENCHMARKRESULT);
    add(benchmarkResultSchema, org.gaia.simulationService.ontology.BenchmarkResult.class);
    ConceptSchema platformLoadSchema = new ConceptSchema(PLATFORMLOAD);
    add(platformLoadSchema, org.gaia.simulationService.ontology.PlatformLoad.class);
    ConceptSchema remoteContainerConfigSchema = new ConceptSchema(REMOTECONTAINERCONFIG);
    add(remoteContainerConfigSchema, org.gaia.simulationService.ontology.RemoteContainerConfig.class);
    ConceptSchema platformAddressSchema = new ConceptSchema(PLATFORMADDRESS);
    add(platformAddressSchema, org.gaia.simulationService.ontology.PlatformAddress.class);
    ConceptSchema objectMapSchema = new ConceptSchema(OBJECTMAPOBJECTS);
    add(objectMapSchema, org.gaia.jade.ontology.ObjectMap.class);
    ConceptSchema pricingSettingSchema = new ConceptSchema(PRICINGSETTING);
    add(pricingSettingSchema, org.gaia.jade.ontology.PricingSetting.class);
    ConceptSchema pricingSettingItemSchema = new ConceptSchema(PRICINGSETTINGITEM);
    add(pricingSettingItemSchema, org.gaia.jade.ontology.JadePricingSettingItem.class);
    ConceptSchema pricersSettingSchema = new ConceptSchema(PRICERSSETTING);
    add(pricersSettingSchema, org.gaia.jade.ontology.JadePricersSetting.class);


    // adding AgentAction(s)
    AgentActionSchema clientUnregisterSchema = new AgentActionSchema(CLIENTUNREGISTER);
    add(clientUnregisterSchema, org.gaia.simulationService.ontology.ClientUnregister.class);
    AgentActionSchema clientTriggerSchema = new AgentActionSchema(CLIENTTRIGGER);
    add(clientTriggerSchema, org.gaia.simulationService.ontology.ClientTrigger.class);
    AgentActionSchema clientRegisterSchema = new AgentActionSchema(CLIENTREGISTER);
    add(clientRegisterSchema, org.gaia.simulationService.ontology.ClientRegister.class);
    AgentActionSchema clientRemoteContainerRequestSchema = new AgentActionSchema(CLIENTREMOTECONTAINERREQUEST);
    add(clientRemoteContainerRequestSchema, org.gaia.simulationService.ontology.ClientRemoteContainerRequest.class);
    AgentActionSchema clientRemoteContainerReplySchema = new AgentActionSchema(CLIENTREMOTECONTAINERREPLY);
    add(clientRemoteContainerReplySchema, org.gaia.simulationService.ontology.ClientRemoteContainerReply.class);
    AgentActionSchema slaveRegisterSchema = new AgentActionSchema(SLAVEREGISTER);
    add(slaveRegisterSchema, org.gaia.simulationService.ontology.SlaveRegister.class);
    AgentActionSchema slaveTriggerSchema = new AgentActionSchema(SLAVETRIGGER);
    add(slaveTriggerSchema, org.gaia.simulationService.ontology.SlaveTrigger.class);
    AgentActionSchema slaveUnregisterSchema = new AgentActionSchema(SLAVEUNREGISTER);
    add(slaveUnregisterSchema, org.gaia.simulationService.ontology.SlaveUnregister.class);
    AgentActionSchema registerReceiptSchema = new AgentActionSchema(REGISTERRECEIPT);
    add(registerReceiptSchema, org.gaia.simulationService.ontology.RegisterReceipt.class);
    AgentActionSchema masterUpdateNoteSchema = new AgentActionSchema(MASTERUPDATENOTE);
    add(masterUpdateNoteSchema, org.gaia.simulationService.ontology.MasterUpdateNote.class);
    AgentActionSchema dispatcherRegisterSchema = new AgentActionSchema(DISPATCHER_REGISTER);
    add(dispatcherRegisterSchema, org.gaia.simulationService.ontology.DispatcherRegister.class);
    AgentActionSchema agentCreationSchema = new AgentActionSchema(AGENTCREATION);
    add(agentCreationSchema, org.gaia.jade.ontology.AgentCreation.class);
    AgentActionSchema pricingAgentCreationSchema = new AgentActionSchema(PRICINGAGENTCREATION);
    add(pricingAgentCreationSchema, org.gaia.jade.ontology.PricerAgentCreation.class);
    AgentActionSchema daoCallSchema = new AgentActionSchema(DAOCALL);
    add(daoCallSchema, org.gaia.jade.ontology.DAOCall.class);
    AgentActionSchema reportObjectNotificationSchema = new AgentActionSchema(REPORTOBJECTNOTIFICATION);
    add(reportObjectNotificationSchema, org.gaia.jade.ontology.DAOCall.class);

    // adding AID(s)

    // adding Predicate(s)


    // adding fields
    osInfoSchema.add(OSINFO_OS_ARCH, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    osInfoSchema.add(OSINFO_OS_NAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    osInfoSchema.add(OSINFO_OS_VERSION, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_CPU_NUMBEROF, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_MEMORY_TOTALMB, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_CPU_SPEEDMHZ, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_CPU_VENDOR, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformPerformanceSchema.add(PLATFORMPERFORMANCE_CPU_MODEL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformTimeSchema.add(PLATFORMTIME_TIMESTAMPASSTRING, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    benchmarkResultSchema.add(BENCHMARKRESULT_BENCHMARKVALUE, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADMEMORYSYSTEM, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADEXCEEDED, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADCPU, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADMEMORYJVM, (TermSchema)getSchema(BasicOntology.FLOAT), ObjectSchema.OPTIONAL);
    platformLoadSchema.add(PLATFORMLOAD_LOADNOTHREADS, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADECONTAINERNAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JVMMEMALLOCINITIAL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JVMMEMALLOCMAXIMUM, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADESERVICES, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADEPORT, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADESHOWGUI, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADEISREMOTECONTAINER, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADEHOST, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_PREVENTUSAGEOFUSEDCOMPUTER, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);
    remoteContainerConfigSchema.add(REMOTECONTAINERCONFIG_JADEJARINCLUDELIST, (TermSchema)getSchema(BasicOntology.STRING), 0, ObjectSchema.UNLIMITED);
    platformAddressSchema.add(PLATFORMADDRESS_IP, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformAddressSchema.add(PLATFORMADDRESS_URL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    platformAddressSchema.add(PLATFORMADDRESS_PORT, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
    platformAddressSchema.add(PLATFORMADDRESS_HTTP4MTP, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    clientTriggerSchema.add(CLIENTTRIGGER_CLIENTBENCHMARKVALUE, benchmarkResultSchema, ObjectSchema.OPTIONAL);
    clientTriggerSchema.add(CLIENTTRIGGER_CLIENTLOAD, platformLoadSchema, ObjectSchema.OPTIONAL);
    clientTriggerSchema.add(CLIENTTRIGGER_TRIGGERTIME, platformTimeSchema, ObjectSchema.OPTIONAL);
    clientRegisterSchema.add(CLIENTREGISTER_CLIENTPERFORMANCE, platformPerformanceSchema, ObjectSchema.OPTIONAL);
    clientRegisterSchema.add(CLIENTREGISTER_CLIENTOS, osInfoSchema, ObjectSchema.OPTIONAL);
    clientRegisterSchema.add(CLIENTREGISTER_CLIENTADDRESS, platformAddressSchema, ObjectSchema.OPTIONAL);
    clientRegisterSchema.add(CLIENTREGISTER_CLIENTTIME, platformTimeSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerRequestSchema.add(CLIENTREMOTECONTAINERREQUEST_REMOTECONFIG, remoteContainerConfigSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEBENCHMARKRESULT, benchmarkResultSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEPID, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTECONTAINERNAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEPERFORMANCE, platformPerformanceSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEOS, osInfoSchema, ObjectSchema.OPTIONAL);
    clientRemoteContainerReplySchema.add(CLIENTREMOTECONTAINERREPLY_REMOTEADDRESS, platformAddressSchema, ObjectSchema.OPTIONAL);
    slaveRegisterSchema.add(SLAVEREGISTER_SLAVEOS, osInfoSchema, ObjectSchema.OPTIONAL);
    slaveRegisterSchema.add(SLAVEREGISTER_SLAVETIME, platformTimeSchema, ObjectSchema.OPTIONAL);
    slaveRegisterSchema.add(SLAVEREGISTER_SLAVEPERFORMANCE, platformPerformanceSchema, ObjectSchema.OPTIONAL);
    slaveRegisterSchema.add(SLAVEREGISTER_SLAVEADDRESS, platformAddressSchema, ObjectSchema.OPTIONAL);
    slaveTriggerSchema.add(SLAVETRIGGER_SLAVEBENCHMARKVALUE, benchmarkResultSchema, ObjectSchema.OPTIONAL);
    slaveTriggerSchema.add(SLAVETRIGGER_SLAVELOAD, platformLoadSchema, ObjectSchema.OPTIONAL);
    slaveTriggerSchema.add(SLAVETRIGGER_TRIGGERTIME, platformTimeSchema, ObjectSchema.OPTIONAL);
    masterUpdateNoteSchema.add(MASTERUPDATENOTE_UPDATEINFOURL, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    agentCreationSchema.add(AGENTCREATIONNAME,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    agentCreationSchema.add(AGENTCREATIONCLASSNAME,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    agentCreationSchema.add(AGENTCREATIONADDNOSUFFIXTONAME,(PrimitiveSchema)getSchema(BasicOntology.BOOLEAN),ObjectSchema.OPTIONAL);
    agentCreationSchema.add(AGENTCREATIONARGS,(PrimitiveSchema)getSchema(BasicOntology.STRING),0,ObjectSchema.UNLIMITED);
    objectMapSchema.add(OBJECTMAPIDS,(PrimitiveSchema)getSchema(BasicOntology.INTEGER),0,ObjectSchema.UNLIMITED);
    objectMapSchema.add(OBJECTMAPOBJECTS,(PrimitiveSchema)getSchema(BasicOntology.STRING),0,ObjectSchema.UNLIMITED);

    pricingSettingItemSchema.add(PRICINGSETTINGITEMPRCINGSETTINGITEMID,(PrimitiveSchema)getSchema(BasicOntology.INTEGER),ObjectSchema.OPTIONAL);
    pricingSettingItemSchema.add(PRICINGSETTINGITEMITEMTYPE,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricingSettingItemSchema.add(PRICINGSETTINGITEMPRICINGFUNCTION,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricingSettingItemSchema.add(PRICINGSETTINGITEMCURRENCY,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricingSettingItemSchema.add(PRICINGSETTINGITEMPRODUCTTYPE,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricingSettingItemSchema.add(PRICINGSETTINGITEMTRADEFILTERNAME,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricingSettingItemSchema.add(PRICINGSETTINGITEMPRODUCTUNDERLYINGID,(PrimitiveSchema)getSchema(BasicOntology.INTEGER),ObjectSchema.OPTIONAL);
    pricingSettingItemSchema.add(PRICINGSETTINGITEMITEMVALUE,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricingSettingItemSchema.add(PRICINGSETTINGITEMITEMVALUEID,(PrimitiveSchema)getSchema(BasicOntology.INTEGER),ObjectSchema.OPTIONAL);

    pricersSettingSchema.add(PRICERSSETTINGPRICERSSETTINGID,(PrimitiveSchema)getSchema(BasicOntology.INTEGER),ObjectSchema.OPTIONAL);
    pricersSettingSchema.add(PRICERSSETTINGPRODUCTTYPE,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricersSettingSchema.add(PRICERSSETTINGTRADEFILTERNAME,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricersSettingSchema.add(PRICERSSETTINGMEASUREGROUP,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricersSettingSchema.add(PRICERSSETTINGPRICER,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);

    pricingSettingSchema.add(PRICINGSETTINGVALUATIONDATE,(PrimitiveSchema)getSchema(BasicOntology.DATE),ObjectSchema.OPTIONAL);
    pricingSettingSchema.add(PRICINGSETTINGPRICINGENVIRONMENTID,(PrimitiveSchema)getSchema(BasicOntology.INTEGER),ObjectSchema.OPTIONAL);
    pricingSettingSchema.add(PRICINGSETTINGNAME,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricingSettingSchema.add(PRICINGSETTINGPRICINGSETTINGITEMCOLLECTION,pricingSettingItemSchema,0,ObjectSchema.UNLIMITED);
    pricingSettingSchema.add(PRICINGSETTINGPRICERSSETTINGCOLLECTION,pricersSettingSchema,0,ObjectSchema.UNLIMITED);
    pricingSettingSchema.add(PRICINGSETTINGMEASURES2D,(PrimitiveSchema)getSchema(BasicOntology.STRING),0,ObjectSchema.UNLIMITED);
    pricingSettingSchema.add(PRICINGSETTINGLISTIDS2D,(PrimitiveSchema)getSchema(BasicOntology.STRING),0,ObjectSchema.UNLIMITED);
    pricingSettingSchema.add(PRICINGSETTINGISREALTIME,(PrimitiveSchema)getSchema(BasicOntology.BOOLEAN),ObjectSchema.OPTIONAL);

    pricingAgentCreationSchema.add(PRICINGAGENTCREATIONNAME,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricingAgentCreationSchema.add(PRICINGAGENTCREATIONCLASSNAME,(PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    pricingAgentCreationSchema.add(PRICINGAGENTCREATIONARGS,(PrimitiveSchema)getSchema(BasicOntology.STRING),0,ObjectSchema.UNLIMITED);
    pricingAgentCreationSchema.add(PRICINGAGENTCREATIONPRICINGSETTING,pricingSettingSchema,ObjectSchema.OPTIONAL);

    daoCallSchema.add(DAOCALLCLASSNAME, (PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    daoCallSchema.add(DAOCALLMETHODNAME, (PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    daoCallSchema.add(DAOCALLARGUMENTS, (PrimitiveSchema)getSchema(BasicOntology.STRING),0,ObjectSchema.UNLIMITED);
    daoCallSchema.add(DAOCALLARGUMENTTYPENAMES, (PrimitiveSchema)getSchema(BasicOntology.STRING),0,ObjectSchema.UNLIMITED);
    daoCallSchema.add(DAOCALLISASYNCHRONE, (PrimitiveSchema)getSchema(BasicOntology.BOOLEAN),ObjectSchema.OPTIONAL);

    reportObjectNotificationSchema.add(REPORTOBJECTNOTIFICATIONOBJECTTYPE, (PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);
    reportObjectNotificationSchema.add(REPORTOBJECTNOTIFICATIONID, (PrimitiveSchema)getSchema(BasicOntology.INTEGER),ObjectSchema.OPTIONAL);
    reportObjectNotificationSchema.add(REPORTOBJECTNOTIFICATIONISNEW, (PrimitiveSchema)getSchema(BasicOntology.BOOLEAN),ObjectSchema.OPTIONAL);
    reportObjectNotificationSchema.add(REPORTOBJECTNOTIFICATIONFROMDATE, (PrimitiveSchema)getSchema(BasicOntology.STRING),ObjectSchema.OPTIONAL);


    // adding name mappings

    // adding inheritance

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
  }
