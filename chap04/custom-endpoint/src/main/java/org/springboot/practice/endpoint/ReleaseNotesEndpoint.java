package org.springboot.practice.endpoint;

import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springboot.practice.model.ReleaseNote;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Endpoint(id = "releaseNotes")
public class ReleaseNotesEndpoint {

  private final Collection<ReleaseNote> releaseNotes;

  @ReadOperation
  public Iterable<ReleaseNote> releaseNotes() {
    return releaseNotes;
  }

  @ReadOperation
  public Object selectCourse(@Selector String version) {
    Optional<ReleaseNote> releaseNoteOptional =
        releaseNotes.stream()
            .filter(releaseNote -> version.equals(releaseNote.getVersion()))
            .findFirst();
    if (releaseNoteOptional.isPresent()) {
      return releaseNoteOptional.get();
    }
    return String.format("No such release version exists : %s", version);
  }

  @DeleteOperation
  public void removeReleaseVersion(@Selector String version) {
    Optional<ReleaseNote> releaseNoteOptional =
        releaseNotes.stream()
            .filter(releaseNote -> version.equals(releaseNote.getVersion()))
            .findFirst();
    if (releaseNoteOptional.isPresent()) {
      releaseNotes.remove(releaseNoteOptional.get());
    }
  }
}
