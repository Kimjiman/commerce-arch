-- ===================
-- product
-- ===================

CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    price BIGINT NOT NULL,
    stock_qty INT NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'ON_SALE',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_id BIGINT DEFAULT 0,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_id BIGINT DEFAULT 0
);

COMMENT ON TABLE product IS '상품';
COMMENT ON COLUMN product.id IS '상품 PK';
COMMENT ON COLUMN product.name IS '상품명';
COMMENT ON COLUMN product.price IS '판매가 (원)';
COMMENT ON COLUMN product.stock_qty IS '재고 수량';
COMMENT ON COLUMN product.status IS '상품 상태 (ON_SALE, SOLD_OUT, DISCONTINUED)';
COMMENT ON COLUMN product.create_time IS '생성 일시';
COMMENT ON COLUMN product.create_id IS '생성자 ID';
COMMENT ON COLUMN product.update_time IS '수정 일시';
COMMENT ON COLUMN product.update_id IS '수정자 ID';

-- ===================
-- orders
-- ===================

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    total_amount BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    delivery_address VARCHAR(500) DEFAULT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_id BIGINT DEFAULT 0,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_id BIGINT DEFAULT 0
);

COMMENT ON TABLE orders IS '주문';
COMMENT ON COLUMN orders.id IS '주문 PK';
COMMENT ON COLUMN orders.user_id IS '주문자 ID (user.id)';
COMMENT ON COLUMN orders.total_amount IS '주문 총액 (원)';
COMMENT ON COLUMN orders.status IS '주문 상태 (PENDING, PAID, SHIPPING, DELIVERED, CANCELLED)';
COMMENT ON COLUMN orders.delivery_address IS '배송지';
COMMENT ON COLUMN orders.create_time IS '생성 일시';
COMMENT ON COLUMN orders.create_id IS '생성자 ID';
COMMENT ON COLUMN orders.update_time IS '수정 일시';
COMMENT ON COLUMN orders.update_id IS '수정자 ID';

-- ===================
-- order_item
-- ===================

CREATE TABLE order_item (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price BIGINT NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_id BIGINT DEFAULT 0,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_id BIGINT DEFAULT 0
);

COMMENT ON TABLE order_item IS '주문 항목';
COMMENT ON COLUMN order_item.id IS '주문 항목 PK';
COMMENT ON COLUMN order_item.order_id IS '주문 FK (orders.id)';
COMMENT ON COLUMN order_item.product_id IS '상품 FK (product.id)';
COMMENT ON COLUMN order_item.quantity IS '주문 수량';
COMMENT ON COLUMN order_item.unit_price IS '주문 시점 단가 (원)';
COMMENT ON COLUMN order_item.create_time IS '생성 일시';
COMMENT ON COLUMN order_item.create_id IS '생성자 ID';
COMMENT ON COLUMN order_item.update_time IS '수정 일시';
COMMENT ON COLUMN order_item.update_id IS '수정자 ID';

-- ===================
-- payment
-- ===================

CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    method VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    paid_at TIMESTAMP DEFAULT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_id BIGINT DEFAULT 0,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_id BIGINT DEFAULT 0
);

COMMENT ON TABLE payment IS '결제';
COMMENT ON COLUMN payment.id IS '결제 PK';
COMMENT ON COLUMN payment.order_id IS '주문 FK (orders.id)';
COMMENT ON COLUMN payment.amount IS '결제 금액 (원)';
COMMENT ON COLUMN payment.method IS '결제 수단 (CARD, TRANSFER)';
COMMENT ON COLUMN payment.status IS '결제 상태 (PENDING, COMPLETED, FAILED, REFUNDED)';
COMMENT ON COLUMN payment.paid_at IS '결제 완료 일시';
COMMENT ON COLUMN payment.create_time IS '생성 일시';
COMMENT ON COLUMN payment.create_id IS '생성자 ID';
COMMENT ON COLUMN payment.update_time IS '수정 일시';
COMMENT ON COLUMN payment.update_id IS '수정자 ID';
