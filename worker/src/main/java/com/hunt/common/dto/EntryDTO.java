package com.hunt.common.dto;

import com.hunt.common.model.Identifiable;
/*
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
*/

import java.time.Instant;
import java.util.UUID;

/**
 * Базовый DTO.
 *
 * @author idurdyev
 */
/*@Getter
@Setter
@ToString
@EqualsAndHashCode*/
public abstract class EntryDTO implements Identifiable {

  /**
   * ID записи
   */
  private UUID id;

  /**
   * Момент создания записи в БД
   */
  private Instant created;

  /**
   * Момент последнего изменения записи в БД
   */
  private Instant updated;

  /**
   * ID пользователя, создавшего запись
   */
  private UUID createdBy;

  /**
   * ID последнего пользователя, изменившего запись
   */
  private UUID updatedBy;
}
