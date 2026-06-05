package com.tushar.projects.prompt_forge.validation;

public enum ServiceError {

    user_already_exists_with_email("User already exists with email"),
    invalid_email("Invalid email"),
    invalid_password("Invalid password"),
    invalid_firstName("Invalid First Name "),
    invalid_userName_or_password("Invalid user name or password"),
    invalid_room_name("Invalid Room Name"),
    invalid_room_id("Invalid RoomId"),
    invalid_room("Room with this Id does not exist"),
    invalid_user_id("Invalid UserId"),
    invalid_user_room("User is not part of this room");

    private final String message;

    ServiceError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
