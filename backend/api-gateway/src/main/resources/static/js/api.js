const API = {

    async get(url) {

        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(await response.text());
        }

        return await response.json();

    },

    async post(url, dados) {

        const response = await fetch(url, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(dados)

        });

        if (!response.ok) {
            throw new Error(await response.text());
        }

        return await response.json();

    }

};