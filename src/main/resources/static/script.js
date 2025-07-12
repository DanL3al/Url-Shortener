async function encurtarUrl() {
    const url = document.getElementById("urlInput").value;
    const resultado = document.getElementById("resultado");
    const erro = document.getElementById("erro");
    const urlCurta = document.getElementById("urlCurta");

    resultado.classList.add("hidden");
    erro.innerText = "";

    try {
        const response = await fetch("http://localhost:8081/api/shorten", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ originalUrl: url })
        });

        if (!response.ok) {
            const msg = await response.text();
            throw new Error(msg || "Erro ao encurtar a URL.");
        }

        const data = await response.json();
        urlCurta.value = data.url;
        resultado.classList.remove("hidden");
    } catch (e) {
        erro.innerText = e.message;
    }
}

function copiar() {
    const input = document.getElementById("urlCurta");
    input.select();
    document.execCommand("copy");
}
