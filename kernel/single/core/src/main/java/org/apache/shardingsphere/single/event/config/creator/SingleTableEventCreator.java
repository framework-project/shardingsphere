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

package org.apache.shardingsphere.single.event.config.creator;

import org.apache.shardingsphere.infra.rule.event.rule.RuleItemChangedEvent;
import org.apache.shardingsphere.mode.event.DataChangedEvent;
import org.apache.shardingsphere.mode.event.DataChangedEvent.Type;
import org.apache.shardingsphere.mode.event.UniqueRuleItemChangedEventCreator;
import org.apache.shardingsphere.single.event.config.AlterSingleTableEvent;
import org.apache.shardingsphere.single.event.config.DropSingleTableEvent;

/**
 * Single table event creator.
 */
public final class SingleTableEventCreator implements UniqueRuleItemChangedEventCreator {
    
    @Override
    public RuleItemChangedEvent create(final String databaseName, final DataChangedEvent event) {
        if (Type.ADDED == event.getType() || Type.UPDATED == event.getType()) {
            return new AlterSingleTableEvent(databaseName, event.getKey(), event.getValue());
        }
        return new DropSingleTableEvent(databaseName);
    }
}