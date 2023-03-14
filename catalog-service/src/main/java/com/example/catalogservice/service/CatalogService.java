package com.example.catalogservice.service;

import com.example.catalogservice.model.Catalog;

public interface CatalogService {
    Iterable<Catalog> getAllCatalogs();
}
