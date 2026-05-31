package se.kth.iv1350.bikerepair.model;

/**
 * Different states of a repair order.
 *
 */
public enum RepairOrderState {
    NEWLY_CREATED,
    READY_FOR_APPROVAL,
    REJECTED,
    ACCEPTED
}