//contact script
$(document).ready(function () {
    $('.nav__links a').on('click', function (e) {
        if (this.hash !== '') {
            e.preventDefault();
            const hash = this.hash;

            $('html, body').animate({
                scrollTop: $(hash).offset().top
            }, 800, function () {
                window.location.hash = hash;
            });
        }
    });
    $(window).on('scroll', function () {
        $('.section__container').each(function () {
            const position = $(this).offset().top;
            const scrollPosition = $(window).scrollTop() + $(window).height();

            if (scrollPosition > position) {
                $(this).addClass('animate');
            }
        });
    });

});


document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("submitButton").addEventListener("click", function (event) {
        if (!confirm('Are you sure you want to add this registration?')) {
            event.preventDefault();
        }
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const imgDiv = document.getElementById("myMap");

    const modal = document.createElement("div");
    modal.style.display = "none";
    modal.style.position = "fixed";
    modal.style.zIndex = "1";
    modal.style.left = "0";
    modal.style.top = "0";
    modal.style.width = "100%";
    modal.style.height = "100%";
    modal.style.overflow = "auto";
    modal.style.backgroundColor = "rgba(0,0,0,0.9)";

    const modalImg = document.createElement("img");
    modalImg.style.margin = "auto";
    modalImg.style.display = "block";
    modalImg.style.width = "80%";
    modalImg.style.maxWidth = "700px";
    modalImg.style.animation = "zoom 0.6s";

    const closeBtn = document.createElement("span");
    closeBtn.innerHTML = "&times;";
    closeBtn.style.position = "absolute";
    closeBtn.style.top = "15px";
    closeBtn.style.right = "35px";
    closeBtn.style.color = "#f1f1f1";
    closeBtn.style.fontSize = "40px";
    closeBtn.style.fontWeight = "bold";
    closeBtn.style.transition = "0.3s";
    closeBtn.style.cursor = "pointer";

    modal.appendChild(modalImg);
    modal.appendChild(closeBtn);
    document.body.appendChild(modal);

    var style = document.createElement('style');
    style.type = 'text/css';
    style.innerHTML = `
    @keyframes zoom {
        from {transform:scale(0)} 
        to {transform:scale(1)}
    }`;
    document.getElementsByTagName('head')[0].appendChild(style);

    imgDiv.style.cursor = "pointer";
    imgDiv.addEventListener("click", function () {
        modal.style.display = "block";
        modalImg.src = imgDiv.style.backgroundImage.slice(5, -2);
    });

    closeBtn.addEventListener("click", function () {
        modal.style.display = "none";
    });
});


document.addEventListener("DOMContentLoaded", function () {
    const textSection = document.querySelector('.text');
    textSection.style.opacity = 0;
    textSection.style.transform = 'translateY(50px)';
    textSection.style.transition = 'opacity 1.5s ease-out, transform 1.5s ease-out';

    setTimeout(() => {
        textSection.style.opacity = 1;
        textSection.style.transform = 'translateY(0)';
        console.log('Animation d\'apparition déclenchée');
    }, 100);

    const buttons = document.querySelectorAll('.button .btn');
    buttons.forEach(button => {
        button.style.transition = 'transform 0.3s ease, background-color 0.3s ease';
        button.addEventListener('mouseenter', () => {
            console.log('Mouse enter sur', button);
            button.style.transform = 'scale(1.2)';
            button.style.backgroundColor = '#ff7f50';
        });
        button.addEventListener('mouseleave', () => {
            console.log('Mouse leave sur', button);
            button.style.transform = 'scale(1)';
            button.style.backgroundColor = '';
        });
    });

    buttons.forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault();
            console.log('Click sur', button);
            button.style.backgroundColor = '#ff4500';
            setTimeout(() => {
                button.style.backgroundColor = '';
            }, 200);
        });
    });
});

    function toggleTable(tableId) {
    var table = document.getElementById(tableId);
    if (table.style.display === "none") {
    table.style.display = "table";
     } else {
    table.style.display = "none";
}


}



//report
function exportPDF() {
    const doc = new jsPDF();

    doc.setProperties({
        title: 'Diabetic Report',
        subject: 'Diabetic Information',
        author: 'Your Name',
        keywords: 'diabetic, report',
        creator: 'Your Organization'
    });

    doc.html(document.querySelector("html"), {
        callback: function (doc) {
            doc.save("diabetic_report.pdf");
        }
    });
}