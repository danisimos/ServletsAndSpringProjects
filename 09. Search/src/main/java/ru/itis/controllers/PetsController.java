package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.dto.PetDto;
import ru.itis.services.PetsService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetsController {
    private final PetsService petsService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PetDto> addPet(@RequestBody PetDto petDto) {
        return petsService.addPet(petDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getPetsPage(Model model) {
        model.addAttribute("pets", petsService.getPets());
        return "pets";
    }
}
