package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * UserProfileRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-05T22:03:09.131657600+01:00[Europe/Warsaw]")
public class UserProfileRequest {

  @JsonProperty("timezone")
  private String timezone;

  public UserProfileRequest timezone(String timezone) {
    this.timezone = timezone;
    return this;
  }

  /**
   * Get timezone
   * @return timezone
  */
  @NotNull 
  @Schema(name = "timezone", example = "Europe/Warsaw", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserProfileRequest userProfileRequest = (UserProfileRequest) o;
    return Objects.equals(this.timezone, userProfileRequest.timezone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timezone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserProfileRequest {\n");
    sb.append("    timezone: ").append(toIndentedString(timezone)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

