﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using objectBatch;


namespace MainView
{
    public partial class MainView : Form
    {
        public MainView()
        {
            InitializeComponent();
        }

        private void MainView_Load(object sender, EventArgs e)
        {
            
        }

        private void scanButton_Click(object sender, EventArgs e)
        {
            List<Device> list = GestionBluetooth.Scan();
            listDevice.DataSource = list;

        }
    }
}
