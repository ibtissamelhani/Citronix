package org.youcode.citronix.exception.Harvest;

public class HarvestAlreadySoldException extends RuntimeException {
  public HarvestAlreadySoldException(String message) {
    super(message);
  }
}
