CREATE TABLE category
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      BIGINT NULL,
    last_updated_at BIGINT NULL,
    is_deleted      BIT(1) NULL,
    name            VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      BIGINT NULL,
    last_updated_at BIGINT NULL,
    is_deleted      BIT(1) NULL,
    title           VARCHAR(255) NULL,
    `description`   VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    category_id     BIGINT NULL,
    image           VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);