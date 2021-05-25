/* Hibernate, Relational Persistence for Idiomatic Java
 *
 * SPDX-License-Identifier: LGPL-2.1-or-later
 * Copyright: Red Hat Inc. and Hibernate Authors
 */
package org.hibernate.reactive;

import io.vertx.db2client.DB2ConnectOptions;
import io.vertx.mssqlclient.MSSQLConnectOptions;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlConnectOptions;
import org.hibernate.reactive.pool.impl.SqlClientPoolConfiguration;

import java.net.URI;

public class UriPoolConfiguration implements SqlClientPoolConfiguration {
    @Override
    public PoolOptions poolOptions() {
        return new PoolOptions();
    }

    @Override
    public SqlConnectOptions connectOptions(URI uri) {
        switch ( uri.getScheme() )
        {
            case "postgresql":
            case "postgres":
                return PgConnectOptions.fromUri( uri.toString() );
            case "mysql":
                return MySQLConnectOptions.fromUri( uri.toString() );
            case "db2":
                return DB2ConnectOptions.fromUri( uri.toString() );
            case "mssql":
            case "sqlserver":
                return MSSQLConnectOptions.fromUri( uri.toString() );
            default:
                throw new IllegalArgumentException();
        }
    }
}
