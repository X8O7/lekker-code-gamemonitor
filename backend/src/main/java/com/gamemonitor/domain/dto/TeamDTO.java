package com.gamemonitor.domain.dto;

public record TeamDTO(Long id, String name, long ownerId, int maxMembers) {
}
