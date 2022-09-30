document.addEventListener('DOMContentLoaded', () => {
    Chart.register(ChartDataLabels);
    const context = document.getElementById('regionChart').getContext('2d');
    const regionChart = () => {
        fetch(`/api/members/region`)
            .then(res => res.json())
            .then(res => {
                    const region = res.regionList;
                    const count = res.countList;
                    new Chart(context, {
                        plugins:[ChartDataLabels],
                        type: 'pie',
                        data: {
                            labels: region,
                            datasets: [
                                {
                                    label: '지역별 사원 수',
                                    data: count,
                                    backgroundColor: [
                                        //색상
                                        'rgba(255, 99, 132, 0.2)',
                                        'rgba(54, 162, 235, 0.2)',
                                        'rgba(255, 206, 86, 0.2)',
                                        'rgba(75, 192, 192, 0.2)',
                                        'rgba(153, 102, 255, 0.2)',
                                        'rgba(255, 159, 64, 0.2)',
                                    ],

                                }
                            ]
                        },
                        options:{
                            plugins:{
                                datalabels:{
                                    color: 'black',
                                    formatter: function (value,context){
                                        // return context.dataIndex + ': ' + Math.round(value*100)+'%';
                                        const totalDatasetSum = context.chart.data.datasets[context.datasetIndex].data.reduce((a,b)=>(a+b),0);
                                        const percentage = value * 100 / totalDatasetSum;
                                        const roundedPercentage = Math.round(percentage*100)/100
                                        return `${roundedPercentage}%`;
                                    }
                                }
                            }
                        }

                    });
                }
            )
    }
    regionChart();
})