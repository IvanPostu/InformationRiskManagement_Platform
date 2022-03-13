
CREATE TABLE [dbo].[sa__answers] (
    [answer_id]     INTEGER IDENTITY(1, 1) NOT NULL,
    [answer]        NVARCHAR (4000) NOT NULL,
    [answer_weight] INTEGER DEFAULT 10,

    CONSTRAINT pk_sa__answers PRIMARY KEY CLUSTERED ([answer_id] ASC)
);

