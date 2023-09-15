CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL COMMENT 'ニックネーム',
    email VARCHAR(50) NOT NULL UNIQUE COMMENT 'メールアドレス',
    created_at DATETIME NOT NULL COMMENT '登録日',
    updated_at DATETIME NOT NULL COMMENT '更新日'
) COMMENT = 'ユーザーTBL';

CREATE TABLE IF NOT EXISTS tasks(
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL COMMENT 'ユーザーID',
    title VARCHAR(128) NOT NULL COMMENT 'タスクタイトル',
    description VARCHAR(256) NOT NULL COMMENT 'タスク説明',
    due_date DATE COMMENT 'タスク期限',
    completed TINYINT(1) NOT NULL DEFAULT 0 COMMENT 'タスクが完了済みか 0:未完了 1:完了',
    created_at DATETIME NOT NULL COMMENT '登録日',
    updated_at DATETIME NOT NULL COMMENT '更新日',
    FOREIGN KEY (user_id) REFERENCES users(user_id)
) COMMENT = 'タスクTBL';