package com.gamemonitor.service;

import com.gamemonitor.api.model.TeamBase;
import com.gamemonitor.domain.dto.TeamDTO;
import com.gamemonitor.domain.model.TeamEntity;
import com.gamemonitor.domain.repository.TeamRepository;
import com.gamemonitor.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final UserService userService;

    public TeamDTO getTeamById(String id) {
        if (!id.isEmpty()) {
            var optional = teamRepository.findById(Long.valueOf(id));

            if (optional.isPresent()) {
                var team = optional.get();
                return mapEntityToDto(team);
            }
        }
        return null;
    }

    public List<TeamDTO> getTeams() {
        var teams = teamRepository.findAll();

        return StreamSupport.stream(teams.spliterator(), false)
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public TeamDTO createTeam(TeamBase teamBase) {
        var newTeam = new TeamEntity();
        newTeam.setName(teamBase.getName());
        newTeam.setMaxMembers(teamBase.getMaxMembers());
        newTeam.setOwner(userService.getUserEntity(Long.valueOf(teamBase.getOwnerId().intValue())));

        var savedTeam = teamRepository.save(newTeam);

        return mapEntityToDto(savedTeam);
    }

    private TeamDTO mapEntityToDto(TeamEntity teamEntity) {
        return new TeamDTO(teamEntity.getId(), teamEntity.getName(), teamEntity.getOwner().getId(), teamEntity.getMaxMembers());
    }
}
