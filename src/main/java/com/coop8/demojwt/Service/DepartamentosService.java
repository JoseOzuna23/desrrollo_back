package com.coop8.demojwt.Service;

import com.coop8.demojwt.Jwt.JwtService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coop8.demojwt.Models.Departamento;
import com.coop8.demojwt.PayloadModels.DepartamentosResponseModel;
import com.coop8.demojwt.Repository.DepartamentosRepository;
import com.coop8.demojwt.Request.DepartamentosRequest;

import com.coop8.demojwt.Response.DepartamentosResponse;
import com.coop8.demojwt.Response.PaginacionResponse;
import com.coop8.demojwt.Response.Response;
import com.coop8.demojwt.Response.ResponseHeader;
import com.coop8.demojwt.Response.SecuredResponse;
import com.coop8.demojwt.Utils.ECodigosRespuestas;
import com.coop8.demojwt.Utils.Util;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class DepartamentosService {

    @Autowired
    DepartamentosRepository departamentosRepository;
    
    @Autowired
    JwtService jwtService;

    public SecuredResponse getById(DepartamentosRequest departamentosRequest) throws Exception {
        log.info("DepartamentosService | getById");
        log.info("__departamentosRequest: " + Util.getJsonFromObject(departamentosRequest));

        DepartamentosResponse departamentosResponse = new DepartamentosResponse();

        Optional<Departamento> departamento = departamentosRepository.findById(departamentosRequest.getId());

        if (departamento.isPresent()) {
            List<Departamento> departamentosList = new ArrayList<>();
            departamentosList.add(departamento.get());

            List<DepartamentosResponseModel> departamentosResponseModels = new ArrayList<>();

            for (Departamento departamentos : departamentosList) {
                DepartamentosResponseModel eu = new DepartamentosResponseModel();
                eu.filterPayloadToSend(departamentos);
                departamentosResponseModels.add(eu);
            }

            departamentosResponse.setDepartamentos(departamentosResponseModels);
        }

        ResponseHeader header = new ResponseHeader();
        Response response = new Response();
        SecuredResponse securedResponse = new SecuredResponse();

        header.setCodResultado(ECodigosRespuestas.SUCCESS.getCodigoRespuesta());
        header.setTxtResultado(ECodigosRespuestas.SUCCESS.getTxtRespuesta());
        response.setHeader(header);
        response.setData(departamentosResponse);

        // Creating the token with extra claims
        String token = Util.getJsonFromObject(response);
        securedResponse.setData(token);

        return securedResponse;
    }

    public SecuredResponse list(DepartamentosRequest departamentosRequest) throws Exception {
        log.info("DepartamentosService | list");
        log.info("__departamentosRequest: " + Util.getJsonFromObject(departamentosRequest));

        List<Departamento> departamentosList = new ArrayList<>();
        int pagina = departamentosRequest.getPaginacion().getPagina() - 1;

        Pageable paging = PageRequest.of(pagina, departamentosRequest.getPaginacion().getCantidad());

        Page<Departamento> pageDepartamentos;

        if (Util.isNullOrEmpty(departamentosRequest.getDescripcion())) {
            pageDepartamentos = departamentosRepository.findAllByOrderByDescripcionAsc(paging);
        } else {
            pageDepartamentos = departamentosRepository
                    .findByDescripcionContainingIgnoreCaseOrderByDescripcionAsc(departamentosRequest.getDescripcion(),
                            paging);
        }

        departamentosList = pageDepartamentos.getContent();

        List<DepartamentosResponseModel> departamentosResponseModels = new ArrayList<>();

        for (Departamento departamentos : departamentosList) {
            DepartamentosResponseModel eu = new DepartamentosResponseModel();
            eu.filterPayloadToSend(departamentos);
            departamentosResponseModels.add(eu);
        }

        DepartamentosResponse departamentosResponse = new DepartamentosResponse();
        departamentosResponse.setDepartamentos(departamentosResponseModels);
        PaginacionResponse pageableResponse = new PaginacionResponse();
        pageableResponse.setTotalItems(pageDepartamentos.getTotalElements());
        pageableResponse.setTotalPages(pageDepartamentos.getTotalPages());
        pageableResponse.setCurrentPages(pageDepartamentos.getNumber() + 1);
        departamentosResponse.setPaginacion(pageableResponse);

        ResponseHeader header = new ResponseHeader();
        Response response = new Response();
        SecuredResponse securedResponse = new SecuredResponse();

        header.setCodResultado(ECodigosRespuestas.SUCCESS.getCodigoRespuesta());
        header.setTxtResultado(ECodigosRespuestas.SUCCESS.getTxtRespuesta());
        response.setHeader(header);
        response.setData(departamentosResponse);

        // Creating the token with extra claims
        String token = Util.getJsonFromObject(response);
        securedResponse.setData(token);

        return securedResponse;
    }

    @Transactional
    public SecuredResponse deleteById(DepartamentosRequest departamentosRequest) throws Exception {
        log.info("DepartamentosService | deleteById");
        log.info("__departamentosRequest: " + Util.getJsonFromObject(departamentosRequest));

        long idDepartamento = departamentosRequest.getId();
        ResponseHeader header = new ResponseHeader();
        Response response = new Response();
        SecuredResponse securedResponse = new SecuredResponse();

        departamentosRepository.deleteById(idDepartamento);
        header.setCodResultado(ECodigosRespuestas.SUCCESS.getCodigoRespuesta());
        header.setTxtResultado(ECodigosRespuestas.SUCCESS.getTxtRespuesta());
        response.setHeader(header);
        response.setData(null);

        // Creating the token with extra claims
        String token = Util.getJsonFromObject(response);
        securedResponse.setData(token);

        return securedResponse;
    }
    public SecuredResponse save(@Valid @RequestBody DepartamentosRequest departamentosRequest) throws Exception {
        log.info("DepartamentosService | save");
        log.info("__departamentosRequest: " + Util.getJsonFromObject(departamentosRequest));



        log.info("__departamentosRequest: " + Util.getJsonFromObject(departamentosRequest));

        ResponseHeader header = new ResponseHeader();
        Response response = new Response();
        SecuredResponse securedResponse = new SecuredResponse();

        // Obtenemos el usuario de SecurityContextHolder
        String usuariosys = SecurityContextHolder.getContext().getAuthentication().getName();

        // Verificamos si es un UPDATE
        if (!Util.isNullOrEmpty(departamentosRequest.getId()) && departamentosRequest.getId() != 0) {
            // UPDATE
            long idDepartamento = departamentosRequest.getId();

            Departamento departamentoUpdate = departamentosRepository.findById(idDepartamento).get();
            departamentoUpdate.setDescripcion(departamentosRequest.getDescripcion().toUpperCase());
            departamentoUpdate.setFechaSistema(new Date());
//            departamentoUpdate.setUsuariosys(usuariosys);

            departamentosRepository.save(departamentoUpdate);

            header.setCodResultado(ECodigosRespuestas.SUCCESS.getCodigoRespuesta());
            header.setTxtResultado(ECodigosRespuestas.SUCCESS.getTxtRespuesta());
            response.setHeader(header);
            response.setData(null);

            // Creating the token with extra claims
            String token = jwtService.getDataFromPayload(response);
            securedResponse.setData(token);

            log.info("__response: " + Util.getJsonFromObject(response));
            log.info("__securedResponse: " + Util.getJsonFromObject(securedResponse));
            log.info("Token generado: " + token);
        } else {
            // No se proporcionó un ID válido para la actualización
            header.setCodResultado(ECodigosRespuestas.ERROR.getCodigoRespuesta());
            header.setTxtResultado("No se proporcionó un ID válido para la actualización");
            response.setHeader(header);
            response.setData(null);

            // Creating the token with extra claims
            String token = jwtService.getDataFromPayload(response);
            securedResponse.setData(token);

            log.error("__response: " + Util.getJsonFromObject(response));
            log.error("__securedResponse: " + Util.getJsonFromObject(securedResponse));
            log.error("Token generado: " + token);
        }

        return securedResponse;
    }

    /**
     * Método que crea un nuevo registro en la tabla departamentos
     * 
     * @param requestData
     * @return {SecuredResponse}
     * @throws Exception
     */
    public SecuredResponse newAction(@Valid @RequestBody DepartamentosRequest departamentosRequest) throws Exception {
        log.info("DepartamentosService | newAction");
        log.info("__departamentosRequest: " + Util.getJsonFromObject(departamentosRequest));

        // Decodificamos el dataRequest y obtenemos el Payload
        log.info("__departamentosRequest: " + Util.getJsonFromObject(departamentosRequest));

        ResponseHeader header = new ResponseHeader();
        Response response = new Response();
        SecuredResponse securedResponse = new SecuredResponse();

        // Obtenemos el usuario de SecurityContextHolder
        String usuariosys = SecurityContextHolder.getContext().getAuthentication().getName();

        // Verificamos si es un INSERT
        if (Util.isNullOrEmpty(departamentosRequest.getId()) || departamentosRequest.getId() == 0) {
            // INSERT
            Departamento departamentoInsert = new Departamento();
            departamentoInsert.setDescripcion(departamentosRequest.getDescripcion().toUpperCase());
            departamentoInsert.setFechaSistema(new Date());
//            departamentoInsert.setUsuariosys(usuariosys);

            departamentosRepository.save(departamentoInsert);

            header.setCodResultado(ECodigosRespuestas.SUCCESS.getCodigoRespuesta());
            header.setTxtResultado(ECodigosRespuestas.SUCCESS.getTxtRespuesta());
            response.setHeader(header);
            response.setData(null);

            // Creating the token with extra claims
            String token = jwtService.getDataFromPayload(response);
            securedResponse.setData(token);

            log.info("__response: " + Util.getJsonFromObject(response));
            log.info("__securedResponse: " + Util.getJsonFromObject(securedResponse));
            log.info("Token generado: " + token);
        } else {
            // Se proporcionó un ID para una operación de creación
            header.setCodResultado(ECodigosRespuestas.ERROR.getCodigoRespuesta());
            header.setTxtResultado("Se proporcionó un ID para una operación de creación");
            response.setHeader(header);
            response.setData(null);

            // Creating the token with extra claims
            String token = jwtService.getDataFromPayload(response);
            securedResponse.setData(token);

            log.error("__response: " + Util.getJsonFromObject(response));
            log.error("__securedResponse: " + Util.getJsonFromObject(securedResponse));
            log.error("Token generado: " + token);
        }

        return securedResponse;
    }
}


