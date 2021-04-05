/**
 * Copyright (C) 2017 Alfresco Software Limited.
 * <p/>
 * This file is part of the Alfresco SDK project.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aimprosoft.platformsample;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.nodelocator.CompanyHomeNodeLocator;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class does nothing except dump some output to <i>system.out</i>.
 * This is a sample taken from Maven Alfresco SDK
 *
 * @author Derek Hulley
 */
public class Demo {
    private ServiceRegistry serviceRegistry;

    public void init() {
        System.out.println("Platform JAR Module class has been loaded");

//        createContractFile();
    }
	private void createContractFile() {
		String acmeModelURI = "http://www.acme.org/model/content/1.0";
		String filename = "ContractA.txt";
		NodeRef parentFolderNodeRef =
				serviceRegistry.getNodeLocatorService().getNode(CompanyHomeNodeLocator.NAME, null, null);

		// Create Node
		QName associationType = ContentModel.ASSOC_CONTAINS;
		QName associationQName = QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI,
				QName.createValidLocalName(filename));
		QName nodeType = ContentModel.TYPE_CONTENT;
		Map<QName, Serializable> nodeProperties = new HashMap<>();
		nodeProperties.put(ContentModel.PROP_NAME, filename);
		nodeProperties.put(QName.createQName(acmeModelURI, "documentId"), "DOC001");
		nodeProperties.put(QName.createQName(acmeModelURI, "securityClassification"), "Company Confidential");
		nodeProperties.put(QName.createQName(acmeModelURI, "contractName"), "The first contract");
		nodeProperties.put(QName.createQName(acmeModelURI, "contractId"), "C001");
		ChildAssociationRef parentChildAssocRef = serviceRegistry.getNodeService().createNode(
				parentFolderNodeRef, associationType, associationQName, nodeType, nodeProperties);

		NodeRef newFileNodeRef = parentChildAssocRef.getChildRef();

		// Set content for node
		boolean updateContentPropertyAutomatically = true;
		ContentWriter writer = serviceRegistry.getContentService().getWriter(newFileNodeRef, ContentModel.PROP_CONTENT,
				updateContentPropertyAutomatically);
		writer.setMimetype(MimetypeMap.MIMETYPE_TEXT_PLAIN);
		writer.setEncoding("UTF-8");
		String fileContent = "Contract A, this contract ...";
		writer.putContent(fileContent);

		// Add an aspect to the node
		Map<QName, Serializable> aspectProperties = new HashMap<QName, Serializable>();
		aspectProperties.put(QName.createQName(acmeModelURI, "publishedDate"), new Date());
		serviceRegistry.getNodeService().addAspect(newFileNodeRef, QName.createQName(acmeModelURI, "webPublished"), aspectProperties);
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
}
