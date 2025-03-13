package org.springboot.practice.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/** 릴리스 노트트 */
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReleaseNote {

  /** 릴리스 버전 */
  @EqualsAndHashCode.Include private String version;

  /** 릴리스 일시 */
  private LocalDate releaseDate;

  /** 커밋 태그 */
  private String commitTag;

  /** 새 기능 */
  private Set<ReleaseItem> newReleases;

  /** 버그 수정 */
  private Set<ReleaseItem> bugFixes;
}
