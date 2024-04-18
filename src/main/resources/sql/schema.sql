CREATE TABLE IF NOT EXISTS mgsip_servers (
    server_id INT auto_increment NOT NULL,
    server_name VARCHAR(32) NOT NULL,
    server_ip VARCHAR(32) NOT NULL,
    server_port INT NOT NULL,
    server_game VARCHAR(32) NOT NULL,
    preview_image VARCHAR(32) NULL,
    check_db INT NOT NULL DEFAULT 0,
    CONSTRAINT mgsip_servers_pk PRIMARY KEY (server_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
