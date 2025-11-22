package com.amigoscode;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;
    private final AiService aiService;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository, AiService aiService) {
        this.softwareEngineerRepository = softwareEngineerRepository;
        this.aiService = aiService;

    }

    public List<SoftwareEngineer> getSoftWareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        String prompt = """
                Based on the programming tech stack %s that %s has given provide the full learning path for this person
                """.formatted(softwareEngineer.getTechStack(), softwareEngineer.getName());
        String chatres = aiService.chat(prompt);

        softwareEngineer.setRecommendation(chatres);


        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getSoftWareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));
    }
    public void deleteSoftwareEngineer(Integer id) {
        boolean exist = softwareEngineerRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException(id + "not found");
        }
        softwareEngineerRepository.deleteById(id);
    }

    public void putSoftwareEngineer(Integer id, SoftwareEngineer update) {

        SoftwareEngineer softwareEngineer = softwareEngineerRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));
        softwareEngineer.setName(update.getName());
        softwareEngineer.setTechStack(update.getTechStack());
        softwareEngineerRepository.save(softwareEngineer);


    }


}


