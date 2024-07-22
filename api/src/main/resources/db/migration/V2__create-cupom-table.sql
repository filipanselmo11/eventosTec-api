CREATE TABLE cupom(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    code VARCHAR(100) NOT NULL,
    discount INTEGER NOT NULL,
    valid TIMESTAMP NOT NULL,
    evento_id UUID,
    FOREIGN KEY (evento_id) REFERENCES evento(id) ON DELETE CASCADE
);