
drop table if exists TICKET;

CREATE TABLE TICKET (
                        id UUID PRIMARY KEY,
                        issueDate TIMESTAMP,
                        issuer VARCHAR(255),
                        hunterId UUID,
                        createdDate TIMESTAMP,
                        updatedDate TIMESTAMP
);

