/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cyclop.service.cassandra.impl;

import com.google.common.collect.ImmutableSortedSet;
import org.cyclop.model.CqlColumnName;
import org.cyclop.model.CqlIndex;
import org.cyclop.model.CqlKeySpace;
import org.cyclop.model.CqlQuery;
import org.cyclop.model.CqlQueryResult;
import org.cyclop.model.CqlTable;
import org.cyclop.service.cassandra.QueryService;
import org.springframework.context.annotation.Primary;

import javax.inject.Inject;
import javax.inject.Named;

/** @author Maciej Miklas */
@Named
@Primary
public class QueryServiceDispatcher implements QueryService {

	@Inject
	@CassandraVersionQualifier(CassandraVersion.VER_2_x)
	private QueryService defaultQs;

	@Inject
	@CassandraVersionQualifier(CassandraVersion.VER_1_x)
	private QueryService fallbackQs;

	@Inject
	private CassandraSessionImpl session;

	private QueryService get() {
		QueryService instance;
		CassandraVersion ver = session.getCassandraVersion();
		if (ver == CassandraVersion.VER_2_x) {
			instance = defaultQs;
		} else {
			instance = fallbackQs;
		}
		return instance;
	}

	@Override
	public void executeSimple(CqlQuery query, boolean updateHistory) {
		get().executeSimple(query, updateHistory);
	}

	@Override
	public ImmutableSortedSet<CqlTable> findTableNames(CqlKeySpace keySpace) {
		return get().findTableNames(keySpace);
	}

	@Override
	public ImmutableSortedSet<CqlIndex> findAllIndexes(CqlKeySpace keySpace) {
		return get().findAllIndexes(keySpace);
	}

	@Override
	public boolean checkTableExists(CqlTable table) {
		return get().checkTableExists(table);
	}

	@Override
	public ImmutableSortedSet<CqlColumnName> findColumnNames(CqlTable table) {
		return get().findColumnNames(table);
	}

	@Override
	public ImmutableSortedSet<CqlColumnName> findAllColumnNames() {
		return get().findAllColumnNames();
	}

	@Override
	public ImmutableSortedSet<CqlKeySpace> findAllKeySpaces() {
		return get().findAllKeySpaces();
	}

	@Override
	public CqlQueryResult execute(CqlQuery query) {
		return get().execute(query);
	}

	@Override
	public CqlQueryResult execute(CqlQuery query, boolean updateHistory) {
		return get().execute(query, updateHistory);
	}
}