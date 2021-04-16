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
package com.aimprosoft.scripts;

import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.LimitBy;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DeleteWidget extends DeclarativeWebScript {
    private SearchService searchService;
    private NodeService nodeService;

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
        Map<String, Object> model = new HashMap<>();
        Map<String, String> templateArgs = req.getServiceMatch().getTemplateVars();

        String name = templateArgs.get("name");
        try {
            SearchParameters sp = createSearchRequest(name);
            ResultSet resultSetRows = searchService.query(sp);
            if (resultSetRows.length() != 0) {
                resultSetRows.iterator()
                        .forEachRemaining(n -> nodeService.deleteNode(n.getNodeRef()));
                model.put("msg", "Object was removed");
            } else {
                model.put("msg", "Not found");
            }
        } catch (Exception e) {
            model.put("msg", "Can't remove object");
        }
        return model;
    }

    private SearchParameters createSearchRequest(String name) {
        Objects.requireNonNull(name);
        SearchParameters sp = new SearchParameters();
        sp.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
        sp.setLanguage(SearchService.LANGUAGE_LUCENE);
        sp.setLimitBy(LimitBy.UNLIMITED);
        String query = "TYPE:\"vh:document\" AND @cm\\:name: \"" + name + "\"";
        sp.setQuery(query);
        return sp;
    }
}