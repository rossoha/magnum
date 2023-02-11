package com.augustnagro.magnum

import javax.sql.DataSource

/** A read & write data repository
  *
  * @tparam EC
  *   'Entity Creator', which should have all fields of E minus those
  *   auto-generated by the database. Can be the same type as E.
  * @tparam E
  *   database entity class
  * @tparam ID
  *   id type of E
  */
open class Repo[EC <: Product, E <: Product: DbEntity, ID](
    ds: DataSource,
    config: DbConfig[EC, E, ID]
) extends ImmutableRepo[E, ID](ds, config):

  /** Deletes an entity using its id */
  def deleteById(id: ID)(using DbCon): Unit = config.deleteById(id)

  /** Deletes ALL entities */
  def truncate()(using DbCon): Unit = config.truncate()

  /** Deletes all entities with an Iterable of ids */
  def deleteAllById(ids: Iterable[ID])(using DbCon): Unit =
    config.deleteAllById(ids)

  /** Insert and return entity E */
  def insert(entityCreator: EC)(using DbCon): E = config.insert(entityCreator)

  /** Insert and return all new entities */
  def insertAll(entityCreators: Iterable[EC])(using DbCon): Vector[E] =
    config.insertAll(entityCreators)

  /** Update the entity */
  def update(entity: E)(using DbCon): Unit = config.update(entity)

  /** Update all entities */
  def updateAll(entities: Iterable[E])(using DbCon): Unit =
    config.updateAll(entities)