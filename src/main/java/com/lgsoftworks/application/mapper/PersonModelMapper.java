package com.lgsoftworks.application.mapper;

import com.lgsoftworks.domain.dto.summary.PersonSummaryDTO;
import com.lgsoftworks.domain.model.Admin;
import com.lgsoftworks.domain.model.Person;
import com.lgsoftworks.domain.model.Player;

import java.util.List;
import java.util.stream.Collectors;

public class PersonModelMapper {

    public static PersonSummaryDTO toPersonSummary(Person person) {
        if (person == null) return null;
        PersonSummaryDTO dto = new PersonSummaryDTO();
        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setCity(person.getCity());
        dto.setAge(person.getAge());
        dto.setDocumentType(person.getDocumentType());
        dto.setDocumentNumber(person.getDocumentNumber());
        dto.setEmail(person.getEmail());
        return dto;
    }

    public static Admin toAdmin(PersonSummaryDTO personSummaryDTO) {
        if (personSummaryDTO == null) return null;
        Admin admin = new Admin();
        admin.setId(personSummaryDTO.getId());
        admin.setFirstName(personSummaryDTO.getFirstName());
        admin.setLastName(personSummaryDTO.getLastName());
        admin.setCity(personSummaryDTO.getCity());
        admin.setAge(personSummaryDTO.getAge());
        admin.setDocumentType(personSummaryDTO.getDocumentType());
        admin.setDocumentNumber(personSummaryDTO.getDocumentNumber());
        admin.setEmail(personSummaryDTO.getEmail());
        return admin;
    }

    public static Player toPlayer(PersonSummaryDTO personSummaryDTO) {
        if (personSummaryDTO == null) return null;
        Player player = new Player();
        player.setId(personSummaryDTO.getId());
        player.setFirstName(personSummaryDTO.getFirstName());
        player.setLastName(personSummaryDTO.getLastName());
        player.setCity(personSummaryDTO.getCity());
        player.setAge(personSummaryDTO.getAge());
        player.setDocumentType(personSummaryDTO.getDocumentType());
        player.setDocumentNumber(personSummaryDTO.getDocumentNumber());
        player.setEmail(personSummaryDTO.getEmail());
        return player;
    }

    public static List<PersonSummaryDTO> toPersonSummaryList(List<Player> persons) {
        if (persons == null) return null;
        return persons.stream()
                .map(PersonModelMapper::toPersonSummary)
                .collect(Collectors.toList());
    }

    public static List<Player> toPlayerList(List<PersonSummaryDTO> persons) {
        if (persons == null) return null;
        return persons.stream()
                .map(PersonModelMapper::toPlayer)
                .collect(Collectors.toList());
    }


}
