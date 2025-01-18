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

export function getStatusClasses(status) {
    switch (status) {
        case "Pendente":
            return "text-yellow-600 bg-yellow-100 px-2 py-1 rounded";
        case "Concluído":
            return "text-green-600 bg-green-100 px-2 py-1 rounded";
        case "Cancelada":
            return "text-red-600 bg-red-100 px-2 py-1 rounded";
        default:
            return "text-blue-600 bg-blue-100 px-2 py-1 rounded";
    }
}
