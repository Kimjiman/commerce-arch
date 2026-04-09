---
description: Scaffold new modules following this project's Layered + Facade pattern. Usage: /new-module {moduleName} [moduleName2] ... (e.g., /new-module payment orderItem)
---

# New Module Scaffold

Generate all 10 files for each module name in `$ARGUMENTS`, following this project's architecture conventions.

## Step 0 — Detect package root and base path

Before generating files, read the actual project structure:
1. Run `find src/main/java -name "*.java" | head -1` and read its `package` declaration
2. Extract the root package (e.g., `com.basicarch`)
3. Derive base path: `src/main/java/{package/path}/` (e.g., `src/main/java/com/basicarch/`)

Use these detected values everywhere. Never hardcode package roots.

## Rules

- `$ARGUMENTS` = space-separated list of module names (e.g., `payment orderItem`)
- Package root: `{detectedPackageRoot}.module.{moduleDir}`
- File path: `{detectedBasePath}module/{moduleDir}/`
- `{moduleDir}` = all lowercase, no separators — Java package convention (e.g., `orderitem`, `payment`)
- `{Name}` = PascalCase — class names (e.g., `Payment`, `OrderItem`)
- `{nameCamel}` = camelCase — variable names (e.g., `payment`, `orderItem`)
- `{table_name}` = snake_case — DB table/column names only (e.g., `payment`, `order_item`)

Repeat all 10 files for each module in the list.

## Files to Generate (10 total)

### 1. `entity/{Name}.java`
```java
package {detectedPackageRoot}.module.{moduleName}.entity;

import {detectedPackageRoot}.base.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "{table_name}")
public class {Name} extends BaseEntity<Long> {
    // TODO: add fields
    @Column(name = "name", nullable = false)
    private String name;
}
```

### 2. `model/{Name}Model.java`
```java
package {detectedPackageRoot}.module.{moduleName}.model;

import {detectedPackageRoot}.base.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class {Name}Model extends BaseModel<Long> {
    // TODO: add fields
    private String name;
}
```

### 3. `model/{Name}SearchParam.java`
```java
package {detectedPackageRoot}.module.{moduleName}.model;

import {detectedPackageRoot}.base.model.BaseSearchParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class {Name}SearchParam extends BaseSearchParam<Long> {
    // TODO: add search fields
    private String name;
}
```

### 4. `repository/{Name}Repository.java`
```java
package {detectedPackageRoot}.module.{moduleName}.repository;

import {detectedPackageRoot}.module.{moduleName}.entity.{Name};
import org.springframework.data.jpa.repository.JpaRepository;

public interface {Name}Repository extends JpaRepository<{Name}, Long>, {Name}RepositoryCustom {
}
```

### 5. `repository/{Name}RepositoryCustom.java`
```java
package {detectedPackageRoot}.module.{moduleName}.repository;

import {detectedPackageRoot}.module.{moduleName}.entity.{Name};
import {detectedPackageRoot}.module.{moduleName}.model.{Name}SearchParam;

import java.util.List;

public interface {Name}RepositoryCustom {
    List<{Name}> findAllBy({Name}SearchParam param);
}
```

### 6. `repository/{Name}RepositoryImpl.java`
```java
package {detectedPackageRoot}.module.{moduleName}.repository;

import {detectedPackageRoot}.module.{moduleName}.entity.{Name};
import {detectedPackageRoot}.module.{moduleName}.entity.Q{Name};
import {detectedPackageRoot}.module.{moduleName}.model.{Name}SearchParam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import {detectedPackageRoot}.base.utils.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class {Name}RepositoryImpl implements {Name}RepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<{Name}> findAllBy({Name}SearchParam param) {
        Q{Name} q = Q{Name}.{nameCamel};
        return queryFactory.selectFrom(q)
                .where(buildWhere(param, q))
                .orderBy(q.id.asc())
                .fetch();
    }

    private BooleanBuilder buildWhere({Name}SearchParam param, Q{Name} q) {
        BooleanBuilder builder = new BooleanBuilder();
        // TODO: add search conditions
        if (StringUtils.isNotBlank(param.getName())) {
            builder.and(q.name.contains(param.getName()));
        }
        return builder;
    }
}
```

### 7. `service/{Name}Service.java`
```java
package {detectedPackageRoot}.module.{moduleName}.service;

import {detectedPackageRoot}.base.service.BaseService;
import {detectedPackageRoot}.module.{moduleName}.entity.{Name};
import {detectedPackageRoot}.module.{moduleName}.model.{Name}SearchParam;
import {detectedPackageRoot}.module.{moduleName}.repository.{Name}Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @Slf4j @RequiredArgsConstructor
public class {Name}Service implements BaseService<{Name}, {Name}SearchParam, Long> {
    private final {Name}Repository {nameCamel}Repository;

    @Override 
    public boolean existsById(Long id) { 
        return {nameCamel}Repository.existsById(id); 
    }
    
    @Override 
    public Optional<{Name}> findById(Long id) { 
        return {nameCamel}Repository.findById(id); 
    }
    
    @Override
    public List<{Name}> findAllBy({Name}SearchParam param) { 
        return {nameCamel}Repository.findAllBy(param);
    }
    
    @Override 
    public {Name} save({Name} entity) { 
        return {nameCamel}Repository.save(entity); 
    }
    
    @Override 
    public {Name} update({Name} entity) { 
        return {nameCamel}Repository.save(entity); 
    }
    
    @Override 
    public void deleteById(Long id) { 
        if (id == null) return;
        {nameCamel}Repository.deleteById(id); 
    }
}
```

### 8. `converter/{Name}Converter.java`
```java
package {detectedPackageRoot}.module.{moduleName}.converter;

import {detectedPackageRoot}.base.converter.TypeConverter;
import {detectedPackageRoot}.module.{moduleName}.entity.{Name};
import {detectedPackageRoot}.module.{moduleName}.model.{Name}Model;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TypeConverter.class})
public interface {Name}Converter {
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "localDateTimeToString")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "localDateTimeToString")
    {Name}Model toModel({Name} entity);

    @Mapping(target = "rowNum", ignore = true)
    @Mapping(source = "createTime", target = "createTime", qualifiedByName = "stringToLocalDateTime")
    @Mapping(source = "updateTime", target = "updateTime", qualifiedByName = "stringToLocalDateTime")
    {Name} toEntity({Name}Model model);

    List<{Name}Model> toModelList(List<{Name}> list);
    List<{Name}> toEntityList(List<{Name}Model> list);
}
```

### 9. `facade/{Name}Facade.java`
```java
package {detectedPackageRoot}.module.{moduleName}.facade;

import {detectedPackageRoot}.base.annotation.Facade;
import {detectedPackageRoot}.base.exception.SystemErrorCode;
import {detectedPackageRoot}.base.exception.ToyAssert;
import {detectedPackageRoot}.module.{moduleName}.converter.{Name}Converter;
import {detectedPackageRoot}.module.{moduleName}.model.{Name}Model;
import {detectedPackageRoot}.module.{moduleName}.model.{Name}SearchParam;
import {detectedPackageRoot}.module.{moduleName}.service.{Name}Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j @Facade @RequiredArgsConstructor
public class {Name}Facade {
    private final {Name}Service {nameCamel}Service;
    private final {Name}Converter {nameCamel}Converter;

    public List<{Name}Model> findAllBy({Name}SearchParam param) {
        return {nameCamel}Converter.toModelList({nameCamel}Service.findAllBy(param));
    }

    public {Name}Model findById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        return {nameCamel}Service.findById(id).map({nameCamel}Converter::toModel).orElse(null);
    }

    public void create({Name}Model model) {
        ToyAssert.notBlank(model.getName(), SystemErrorCode.REQUIRED, "name is required.");
        {nameCamel}Service.save({nameCamel}Converter.toEntity(model));
    }

    public void update({Name}Model model) {
        ToyAssert.notNull(model.getId(), SystemErrorCode.REQUIRED, "ID is required.");
        ToyAssert.notBlank(model.getName(), SystemErrorCode.REQUIRED, "name is required.");
        {nameCamel}Service.update({nameCamel}Converter.toEntity(model));
    }

    @Transactional
    public void deleteById(Long id) {
        ToyAssert.notNull(id, SystemErrorCode.REQUIRED, "ID is required.");
        {nameCamel}Service.deleteById(id);
    }
}
```

### 10. `controller/{Name}Controller.java`
```java
package {detectedPackageRoot}.module.{moduleName}.controller;

import {detectedPackageRoot}.module.{moduleName}.facade.{Name}Facade;
import {detectedPackageRoot}.module.{moduleName}.model.{Name}Model;
import {detectedPackageRoot}.module.{moduleName}.model.{Name}SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{moduleName}")
@RequiredArgsConstructor
public class {Name}Controller {
    private final {Name}Facade {nameCamel}Facade;

    @GetMapping
    public List<{Name}Model> selectList({Name}SearchParam param) { return {nameCamel}Facade.findAllBy(param); }

    @GetMapping("/{id}")
    public {Name}Model selectById(@PathVariable Long id) { return {nameCamel}Facade.findById(id); }

    @PostMapping
    public void create(@RequestBody {Name}Model model) { {nameCamel}Facade.create(model); }

    @PutMapping
    public void update(@RequestBody {Name}Model model) { {nameCamel}Facade.update(model); }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) { {nameCamel}Facade.deleteById(id); }
}
```

## After Generation

For each module generated, remind the user to:
1. Add fields to `{Name}.java` and `{Name}Model.java`
2. Create Flyway migration: `src/main/resources/db/migration/V{next}__{table_name}.sql`
3. Run build to generate QueryDSL Q-classes: `JAVA_HOME=/c/java/jdk-21.0.10+7 ./gradlew clean build`
