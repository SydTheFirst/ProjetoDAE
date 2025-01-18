export function formatDate(timestamp) {
    if (!timestamp) return "Data inválida";
    const date = new Date(parseInt(timestamp, 10)); // Certifique-se de converter para número

    const datePart = date.toLocaleDateString("pt-PT", {
        day: "2-digit",
        month: "2-digit",
        year: "numeric",
    });

    const timePart = date.toLocaleTimeString("pt-PT", {
        hour: "2-digit",
        minute: "2-digit",
        hour12: false, // Usa formato de 24 horas
    });

    return `${datePart} | ${timePart}`;
}