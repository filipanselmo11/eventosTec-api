package com.eventostec.api.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.eventostec.api.domain.evento.Evento;
import com.eventostec.api.domain.evento.EventoRepository;
import com.eventostec.api.domain.evento.EventoRequestDto;
import com.eventostec.api.domain.evento.EventoResponseDto;

@Service
public class EventoService {

    @Value("${aws.bucketname}")
    private String bucketname;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private EventoRepository eventoRepository;

    public Evento criarEvento(EventoRequestDto data) {
        String imgUrl = null;

        if(data.image() != null) {
            this.uploadImage(data.image());
        }

        Evento novoEvento = new Evento();
        novoEvento.setTitle(data.title());
        novoEvento.setDescription(data.description());
        novoEvento.setEventUrl(data.eventoUrl());
        novoEvento.setDate(new Date(data.date()));
        novoEvento.setImgUrl(imgUrl);
        novoEvento.setRemote(data.remote());

        eventoRepository.save(novoEvento);

        return novoEvento;
    }

    // public List<EventoResponseDto> getUpComingEvents(int page, int size) {
    //     Pageable pageable = PageRequest.of(page, size);
    //     Page<EventAddressProjection> eventsPage = this.eventoRepository.findUpComingEvents(new Date(), pageable);
    //     return eventsPage.map(event -> new EventoResponseDto())
    // }

    private String uploadImage(MultipartFile file) {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        try {
            File file1 = this.convertMultipartFile(file);
            s3Client.putObject(bucketname,fileName, file);
            file.delete();
            return s3Client.getUrl(bucketname, fileName).toString();
        } catch (Exception e) {
            System.out.println("Erro ao subir arquivo");
            return null;
        }
    }

    private File convertMultipartFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
