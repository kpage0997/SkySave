package com.csc340.provider;

import com.csc340.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<Provider> findAllProviders() {
        return providerRepository.findAll();
    }

    public void deleteByName(String name) {
        Provider provider = providerRepository.findByName(name).orElseThrow(null);
        providerRepository.delete(provider);
    }

    public void saveProvider(Provider provider) {
        providerRepository.save(provider);
    }

    public Provider findByName(String providerName) {
        return providerRepository.findByName(providerName).orElseThrow(null);
    }
}