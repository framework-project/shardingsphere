/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.proxy.backend.hbase.handler;

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.infra.binder.context.statement.SQLStatementContext;
import org.apache.shardingsphere.infra.binder.context.statement.SQLStatementContextFactory;
import org.apache.shardingsphere.infra.executor.sql.execute.result.update.UpdateResult;
import org.apache.shardingsphere.proxy.backend.handler.data.DatabaseBackendHandler;
import org.apache.shardingsphere.proxy.backend.hbase.converter.HBaseOperationConverter;
import org.apache.shardingsphere.proxy.backend.hbase.converter.HBaseOperationConverterFactory;
import org.apache.shardingsphere.proxy.backend.hbase.result.update.HBaseUpdater;
import org.apache.shardingsphere.proxy.backend.response.header.update.UpdateResponseHeader;
import org.apache.shardingsphere.sql.parser.statement.core.statement.SQLStatement;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

/**
 * HBase backend updater handler.
 */
@RequiredArgsConstructor
public final class HBaseBackendUpdateHandler implements DatabaseBackendHandler {
    
    private final SQLStatement sqlStatement;
    
    private final HBaseUpdater updater;
    
    @Override
    public UpdateResponseHeader execute() throws SQLException {
        SQLStatementContext sqlStatementContext = SQLStatementContextFactory.newInstance(null, Collections.emptyList(), sqlStatement, "");
        HBaseOperationConverter converter = HBaseOperationConverterFactory.newInstance(sqlStatementContext);
        Collection<UpdateResult> updateResults = updater.executeUpdate(converter.convert());
        return new UpdateResponseHeader(sqlStatement, updateResults);
    }
}
